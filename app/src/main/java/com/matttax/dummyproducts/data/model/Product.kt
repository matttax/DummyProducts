package com.matttax.dummyproducts.data.model

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
    val discountPercentage: Double,

    @SerializedName("rating")
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
