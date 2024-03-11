package com.matttax.dummyproducts.presentation.model

sealed interface ProductSingleEvent {
    data class ProductsLoadingError(val errorMessage: String) : ProductSingleEvent
    object CategoriesLoadingError : ProductSingleEvent
    object ListUpdated : ProductSingleEvent
}
