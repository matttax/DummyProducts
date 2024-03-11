package com.matttax.dummyproducts.data

import androidx.paging.*
import com.matttax.dummyproducts.data.model.ProductLoadingException.Companion.toProductException
import com.matttax.dummyproducts.data.model.toDomainCategories
import com.matttax.dummyproducts.data.model.toDomainProduct
import com.matttax.dummyproducts.data.model.toDomainProductList
import com.matttax.dummyproducts.domain.ProductDomainModel
import com.matttax.dummyproducts.data.paging.PagingConsts
import com.matttax.dummyproducts.data.paging.ProductPagingSource
import com.matttax.dummyproducts.domain.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi
) {
    fun getProducts(): Flow<PagingData<ProductDomainModel>> {
        return buildPager {
            productApi.getAllProducts(
                skip = it
            ).toDomainProductList()
        }.flow
    }

    fun getProducts(query: String): Flow<PagingData<ProductDomainModel>> {
        return buildPager {
            productApi.searchProducts(
                text = query,
                skip = it
            ).toDomainProductList()
        }.flow
    }

    fun getByCategory(categoryName: String): Flow<PagingData<ProductDomainModel>> {
        return buildPager {
            productApi.getProductsByCategory(
                categoryName = categoryName,
                skip = it
            ).toDomainProductList()
        }.flow
    }

    fun getCategories(): Flow<Result<Categories>> {
        return flow {
            try {
                emit(Result.success(productApi.getCategories().toDomainCategories()))
            } catch (ex: Exception) {
                emit(Result.failure(ex))
            }
        }
    }

    fun getProductById(id: Long): Flow<Result<ProductDomainModel>> {
        return flow {
            try {
                emit(Result.success(productApi.getProductById(id).toDomainProduct()))
            } catch (ex: Exception) {
                emit(Result.failure(ex.toProductException()))
            }
        }
    }

    private fun buildPager(
        fetchProducts: suspend (key: Int) -> List<ProductDomainModel>
    ): Pager<Int, ProductDomainModel> {
        return Pager(
            config = PagingConfig(
                pageSize = PagingConsts.DEFAULT_PAGE_SIZE,
                prefetchDistance = PagingConsts.PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                ProductPagingSource(fetchProducts)
            }
        )
    }
}
