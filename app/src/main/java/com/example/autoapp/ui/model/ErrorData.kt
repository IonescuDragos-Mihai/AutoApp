package com.example.autoapp.ui.model

sealed class ErrorData(open val code: Int, open val message: String = "") {
    object GeneralError : ErrorData(0, "")
}