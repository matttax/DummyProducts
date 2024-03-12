package com.matttax.dummyproducts.data

import com.matttax.dummyproducts.data.model.Product
import com.matttax.dummyproducts.data.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getAllProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 20
    ): ProductsResponse

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") text: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 20
    ): ProductsResponse

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") categoryName: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 20
    ): ProductsResponse

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Long
    ): Product

    companion object {
        const val BASE_URL = "https://dummyjson.com"
    }
}
