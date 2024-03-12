package com.matttax.dummyproducts.di

import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.domain.usecases.GetAllProductsUseCase
import com.matttax.dummyproducts.domain.usecases.GetCategoriesUseCase
import com.matttax.dummyproducts.domain.usecases.GetProductByIdUseCase
import com.matttax.dummyproducts.domain.usecases.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(productsRepository: ProductsRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideSearchProductsUseCase(productsRepository: ProductsRepository): SearchProductsUseCase {
        return SearchProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(productsRepository: ProductsRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCase(productsRepository: ProductsRepository): GetProductByIdUseCase {
        return GetProductByIdUseCase(productsRepository)
    }
}
