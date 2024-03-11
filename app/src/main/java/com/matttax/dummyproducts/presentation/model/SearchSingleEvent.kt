package com.matttax.dummyproducts.presentation.model

sealed interface SearchSingleEvent {
    data class ProductsLoadingError(val errorMessage: String) : SearchSingleEvent
    object CategoriesLoadingError : SearchSingleEvent
    object ListUpdated : SearchSingleEvent
}
