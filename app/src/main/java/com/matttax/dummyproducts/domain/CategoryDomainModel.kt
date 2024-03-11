package com.matttax.dummyproducts.domain

@JvmInline
value class CategoryDomainModel(
    val name: String
)

typealias Categories = List<CategoryDomainModel>
