package com.matttax.dummyproducts.domain

import androidx.paging.PagingData
import com.matttax.dummyproducts.domain.model.Categories
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<PagingData<ProductDomainModel>>
    fun getProducts(query: String): Flow<PagingData<ProductDomainModel>>
    fun getByCategory(categoryName: String): Flow<PagingData<ProductDomainModel>>
    fun getCategories(): Flow<Result<Categories>>
    fun getProductById(id: Long): Flow<Result<ProductDomainModel>>
}
