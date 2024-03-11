package com.matttax.dummyproducts.data.model

import retrofit2.HttpException

sealed class ProductLoadingException : Throwable() {
    data class NetworkException(override val message: String) : ProductLoadingException()
    object NotFoundException : ProductLoadingException()
    object UnknownException : ProductLoadingException()

    companion object {
        fun Throwable.toProductException() : ProductLoadingException {
            return when {
                this is HttpException && message?.contains(NOT_FOUND_CODE) == true -> NotFoundException
                this is HttpException -> NetworkException(message.toString())
                else -> UnknownException
            }
        }
        private const val NOT_FOUND_CODE = "404"
    }
}
