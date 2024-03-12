package com.matttax.dummyproducts.domain.usecases

import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(id: Long): Flow<Result<ProductDomainModel>> {
        return productsRepository.getProductById(id)
    }
}
