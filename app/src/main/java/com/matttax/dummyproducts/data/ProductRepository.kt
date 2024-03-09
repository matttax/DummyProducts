package com.matttax.dummyproducts.data

import androidx.paging.*
import com.matttax.dummyproducts.Product
import com.matttax.dummyproducts.data.paging.PaginationConsts
import com.matttax.dummyproducts.data.paging.ProductPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi
) {
    fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PaginationConsts.DEFAULT_PAGE_SIZE,
                prefetchDistance = PaginationConsts.PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                ProductPagingSource(productApi)
            }
        ).flow
    }
}
