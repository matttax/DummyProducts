package com.matttax.dummyproducts.data

import com.matttax.dummyproducts.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getAllProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 20
    ): ProductsResponse

    companion object {
        const val BASE_URL = "https://dummyjson.com"
    }
}
