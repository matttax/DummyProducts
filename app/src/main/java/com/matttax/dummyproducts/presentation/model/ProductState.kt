package com.matttax.dummyproducts.presentation.model

import com.matttax.dummyproducts.domain.model.ProductDomainModel

sealed interface ProductState {
    object NotFound : ProductState
    object Loading : ProductState
    data class NetworkError(val message: String) : ProductState
    data class Result(val product: ProductDomainModel) : ProductState

    val isError: Boolean
        get() {
            return this is NetworkError || this is NotFound
        }
    companion object {
        const val NETWORK_ERROR_DEFAULT_MESSAGE = "Network error"
    }
}
