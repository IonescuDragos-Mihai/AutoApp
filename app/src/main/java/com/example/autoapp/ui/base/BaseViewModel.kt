package com.example.autoapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.autoapp.ui.model.FetchDataResult
import com.example.autoapp.utils.SingleLiveEvent

open class BaseViewModel<T> : ViewModel() {

    val singleDataSource: SingleLiveEvent<FetchDataResult<T>> = SingleLiveEvent()

}