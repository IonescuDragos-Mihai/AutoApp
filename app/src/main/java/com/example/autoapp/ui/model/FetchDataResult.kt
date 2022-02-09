package com.example.autoapp.ui.model

data class FetchDataResult<T>(
    val state: FetchState,
    val data: T? = null,
    val error: ErrorData? = null
) {
    enum class FetchState {
        LOADING,
        IDLE,
        ERROR
    }

    companion object {
        fun <T> success(data: T, message: ErrorData? = null): FetchDataResult<T> {
            return FetchDataResult(FetchState.IDLE, data, message)
        }

        fun <T> error(message: ErrorData?, data: T? = null): FetchDataResult<T> {
            return FetchDataResult(FetchState.ERROR, data, message)
        }

        fun <T> error(data: T? = null): FetchDataResult<T> {
            return FetchDataResult(FetchState.LOADING, data, null)
        }
    }
}