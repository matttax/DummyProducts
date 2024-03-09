package com.matttax.dummyproducts.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matttax.dummyproducts.Product
import com.matttax.dummyproducts.data.ProductApi
import retrofit2.HttpException
import java.io.IOException

class ProductPagingSource(
    private val productDataSource: ProductApi,
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentSkip = params.key ?: 0
            val products = productDataSource.getAllProducts(
                skip = currentSkip
            ).products
            LoadResult.Page(
                data = products,
                prevKey = getPrevKey(currentSkip),
                nextKey = getNextKey(products.isEmpty(), currentSkip)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    private fun getPrevKey(currentSkip: Int): Int? {
        return if (currentSkip == 0) {
            null
        } else {
            currentSkip - PaginationConsts.DEFAULT_PAGE_SIZE
        }
    }

    private fun getNextKey(isListEmpty: Boolean, currentSkip: Int): Int? {
        return if (isListEmpty) {
            null
        } else {
            currentSkip + PaginationConsts.DEFAULT_PAGE_SIZE
        }
    }
}
