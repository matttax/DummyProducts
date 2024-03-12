package com.matttax.dummyproducts.domain.usecases

import androidx.paging.PagingData
import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val productRepository: ProductsRepository
) {
    operator fun invoke(): Flow<PagingData<ProductDomainModel>> {
        return productRepository.getProducts()
    }
}
