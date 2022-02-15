package com.example.autoapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.autoapp.R

abstract class BaseFragment<VB : ViewBinding>(private val fragmentInflate: FragmentInflate<VB>) :
    Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = fragmentInflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    protected fun navController(): NavController = requireNotNull(
        Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
    ) {
        "NavController not found"
    }


    abstract fun initViews()
}