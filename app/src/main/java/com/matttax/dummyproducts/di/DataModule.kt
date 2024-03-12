package com.matttax.dummyproducts.di

import com.matttax.dummyproducts.data.ProductRepositoryImpl
import com.matttax.dummyproducts.domain.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindProductsRepository(productsRepositoryImpl: ProductRepositoryImpl): ProductsRepository
}
