package com.lemon.team.electronics.model.network

import com.google.gson.JsonElement
import com.lemon.team.electronics.model.orderResponse.OrderResponse
import com.lemon.team.electronics.model.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.model.response.*
import retrofit2.Response
import retrofit2.http.*

interface ProductService {

    @GET("resources/categories/")
    suspend fun getCategories(): Response<List<CategoryResponse>>

    @GET("resources/products/category-id/")
    suspend fun getProductsByCategoryId(
        @Query ("categoryId") categoryId: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<ProductsResponse>

    @GET("resources/products/search/")
    suspend fun getProductByName(
        @Query ("productName") productName: String,
        @Query ("page") page: Int,
        @Query ("sort_by") sortBy: String
    ): Response<ProductsResponse>

    @GET("resources/products/recommended/")
    suspend fun getRecommendedProducts(): Response<List<Product>>

    @GET("resources/products/product")
    suspend fun getProductById(
        @Query ("productId") productId: String
    ): Response<Product>

    @GET("resources/products/product-home-screen-images")
    suspend fun getHomeImages(): Response<List<HomeImage>>

    @POST("resources/orders/add-order/")
    suspend fun makeOrder(@Body order: JsonElement): Response<OrderResponse>

    @GET("resources/orders/checkStatus/{phoneNumber}")
    suspend fun trackOrder(@Path("phoneNumber") phoneNumber: String): Response<List<OrderTrackingResponse>>

}