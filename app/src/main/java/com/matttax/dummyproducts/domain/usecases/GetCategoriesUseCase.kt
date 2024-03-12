package com.matttax.dummyproducts.domain.usecases

import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.domain.model.Categories
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val productRepository: ProductsRepository
) {
    operator fun invoke(): Flow<Result<Categories>> {
        return productRepository.getCategories()
    }
}
