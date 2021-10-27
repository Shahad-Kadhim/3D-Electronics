package com.lemon.team.electronics.model.network

import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.productsByCategoryIdResponse
import com.lemon.team.electronics.model.response.recommended.RecommendedProductsResponse
import com.lemon.team.electronics.model.response.search.ProductByNameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("resources/categories/")
    suspend fun getCategories(): Response<CategoriesResponse>


    @GET("resources/products/category-id/")
    suspend fun getProductsByCategoryId(
        @Query ("categoryId") categoryId: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<productsByCategoryIdResponse>


    @GET("resources/products/search/")
    suspend fun getProductByName(
        @Query ("productName") productName: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<ProductByNameResponse>


    @GET("resources/products/recommended/")
    suspend fun getRecommendedProducts(): Response<RecommendedProductsResponse>


    @GET("resources/products/product")
    suspend fun getProductById(
        @Query ("productId") productId: String
    ): Response<com.lemon.team.electronics.model.response.productById.ProductByIdResponse>

}