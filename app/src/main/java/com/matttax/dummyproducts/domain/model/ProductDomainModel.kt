package com.matttax.dummyproducts.domain.model

import androidx.annotation.FloatRange
import com.google.gson.annotations.SerializedName

data class ProductDomainModel(
    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val inStock: Int,
    val brand: String,
    val category: String,
    val thumbnailUri: String,
    val imageUris: List<String>,
    @FloatRange(from = 0.0, to = 100.0) val discountPercentage: Double,
    @FloatRange(from = 0.0, to = 5.0) val rating: Double,
)
