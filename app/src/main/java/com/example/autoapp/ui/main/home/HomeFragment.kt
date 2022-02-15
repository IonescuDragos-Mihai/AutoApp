package com.example.autoapp.ui.main.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import com.bumptech.glide.Glide
import com.example.autoapp.databinding.FragmentHomeBinding
import com.example.autoapp.ui.base.BaseFragment
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    View.OnClickListener {

    override fun initViews() {
        binding.loadAnImage.setOnClickListener(this)
        binding.buttonStartDetection.setOnClickListener(this)
    }

    private fun getObjectDetector(): ObjectDetector {
        return ObjectDetection.getClient(getSettingForObjectDetector())
    }

    private fun getSettingForObjectDetector(): ObjectDetectorOptions {
        return ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableClassification()
            .build()

    }

    private fun loadImage() {
        Glide.with(this)
            .load("https://developers.google.com/ml-kit/vision/object-detection/images/640px-shoes.jpg")
            .into(binding.imageViewForDetector)
    }

    private fun getBitmap(): Bitmap {
        return (binding.imageViewForDetector.drawable as BitmapDrawable).bitmap
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.loadAnImage.id -> loadImage()
            binding.buttonStartDetection.id -> startObjectDetection()
        }
    }

    private fun startObjectDetection() {
        val image = InputImage.fromBitmap(getBitmap(), 0)
        getObjectDetector().process(image).addOnSuccessListener { list ->
            list.forEach {
                it.labels.forEach { labels ->
                    binding.textViewDetections.text = labels.text
                }
            }
        }.addOnFailureListener {
            binding.textViewDetections.text = it.toString()
        }
    }

}