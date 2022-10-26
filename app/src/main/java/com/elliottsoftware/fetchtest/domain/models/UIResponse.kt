package com.elliottsoftware.fetchtest.domain.models

sealed class UIResponse<out T> {
    object Loading:UIResponse<Nothing>()

    data class Success<out T>(
        val data:T
    ):UIResponse<T>()

    data class Failure(
        val e:Exception
    ):UIResponse<Nothing>()
}