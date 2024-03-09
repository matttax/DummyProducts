package com.matttax.dummyproducts

import androidx.annotation.FloatRange
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("discountPercentage")
    @FloatRange(from = 0.0, to = 100.0)
    val discountPercentage: Double,

    @SerializedName("rating")
    @FloatRange(from = 0.0, to = 5.0)
    val rating: Double,

    @SerializedName("stock")
    val inStock: Int,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("thumbnail")
    val thumbnailUri: String,

    @SerializedName("images")
    val imageUris: List<String>
)

data class ProductsResponse(
    val products: List<Product>
)