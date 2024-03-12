package com.matttax.dummyproducts.presentation.model

import androidx.compose.runtime.Immutable
import com.matttax.dummyproducts.domain.model.ProductDomainModel

@Immutable
data class ProductUiModel(
    val id: Long,
    val title: String,
    val description: String,
    val thumbnailUri: String,
    val price: Double,
    val oldPrice: Double,
    val inStock: Int,
    val rating: Float
)

fun ProductDomainModel.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id,
        title = title,
        description = description,
        thumbnailUri = thumbnailUri,
        price = price,
        oldPrice = price * (100 / (100 - discountPercentage)),
        inStock = inStock,
        rating = rating.toFloat()
    )
}
