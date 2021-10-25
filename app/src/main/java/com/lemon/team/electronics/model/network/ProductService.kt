package com.lemon.team.electronics.model.network

import com.lemon.team.electronics.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("resources/categories/")
    suspend fun getCategory(): Response<CategoryResponse>


    @GET("resources/products/category-id/")
    suspend fun getProductFromCategory(
        @Query ("category_id") categoryId: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<ProductContentResponse>


    @GET("resources/products/search/")
    suspend fun getProductFromSearchResult(
        @Query ("product_name") productName: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<ProductSearchResultResponse>


    @GET("resources/products/recommended/")
    suspend fun getRecommendProducts(): Response<ProductContentResponse>


    @GET("resources/products/product")
    suspend fun getProductContent(
        @Query ("product_id") productId: String
    ): Response<ProductContentResponse>

}
