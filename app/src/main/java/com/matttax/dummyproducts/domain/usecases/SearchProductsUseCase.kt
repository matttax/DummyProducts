package com.matttax.dummyproducts.domain.usecases

import androidx.paging.PagingData
import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(
    private val productRepository: ProductsRepository
) {
    operator fun invoke(input: String): Flow<PagingData<ProductDomainModel>> {
        return productRepository.getProducts(input)
    }
}