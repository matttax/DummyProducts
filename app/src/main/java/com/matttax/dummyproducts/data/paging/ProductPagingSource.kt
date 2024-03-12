package com.matttax.dummyproducts.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import retrofit2.HttpException
import java.io.IOException

class ProductPagingSource(
    private val fetchProducts: suspend (key: Int) -> List<ProductDomainModel>,
) : PagingSource<Int, ProductDomainModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDomainModel> {
        return try {
            val currentSkip = params.key ?: 0
            val products = fetchProducts(currentSkip)
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

    override fun getRefreshKey(state: PagingState<Int, ProductDomainModel>): Int? {
        return state.anchorPosition
    }

    private fun getPrevKey(currentSkip: Int): Int? {
        return if (currentSkip == 0) {
            null
        } else {
            currentSkip - PagingConsts.DEFAULT_PAGE_SIZE
        }
    }

    private fun getNextKey(isListEmpty: Boolean, currentSkip: Int): Int? {
        return if (isListEmpty) {
            null
        } else {
            currentSkip + PagingConsts.DEFAULT_PAGE_SIZE
        }
    }
}
