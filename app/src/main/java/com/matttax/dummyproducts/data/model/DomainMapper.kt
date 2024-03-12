package com.matttax.dummyproducts.data.model

import com.matttax.dummyproducts.domain.model.CategoryDomainModel
import com.matttax.dummyproducts.domain.model.ProductDomainModel

fun Product.toDomainProduct(): ProductDomainModel {
    return ProductDomainModel(
        id, title, description, price, inStock, brand, category, thumbnailUri, imageUris, discountPercentage, rating
    )
}

fun ProductsResponse.toDomainProductList(): List<ProductDomainModel> {
    return products.map { it.toDomainProduct() }
}

fun List<String>.toDomainCategories(): List<CategoryDomainModel> {
    return map { CategoryDomainModel(it) }
}
