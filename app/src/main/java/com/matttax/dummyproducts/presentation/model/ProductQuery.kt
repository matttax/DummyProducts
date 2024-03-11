package com.matttax.dummyproducts.presentation.model

data class ProductQuery(
    val text: String = "",
    val categories: List<String>? = null
) {
    companion object {
        val GET_ALL_QUERY = ProductQuery()
    }
}
