package com.lemon.team.electronics.data

import com.google.gson.JsonElement
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.data.local.WishItem
import com.lemon.team.electronics.data.local.Companies
import com.lemon.team.electronics.data.local.CompaniesImgUrl
import com.lemon.team.electronics.data.remote.order.OrderedProduct
import com.lemon.team.electronics.data.remote.orderResponse.OrderResponse
import com.lemon.team.electronics.data.remote.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.data.remote.response.CategoryResponse
import com.lemon.team.electronics.data.remote.response.HomeImage
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.data.remote.response.ProductsResponse
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCategories(): Flow<State<List<CategoryResponse>?>>


    fun getProductsByCategoryId(
        categoryId: String,
        page: Int,
        sortBy: String = Constants.SORT_BY_CREATED_DATE
    ): Flow<State<ProductsResponse?>>

    fun getProductByName(
        productName: String,
        page: Int,
        sortBy: String = Constants.SORT_BY_CREATED_DATE
    ): Flow<State<ProductsResponse?>>

    fun getRecommendedProducts(): Flow<State<List<Product>?>>

    fun getProductById(productId: String): Flow<State<Product?>>

    fun getProductsInCart(): Flow<State<ProductsResponse?>>


    suspend fun getOrderedProducts(): List<OrderedProduct>

    suspend fun clearCart()

    fun trackOrder(phoneNumber: String): Flow<State<List<OrderTrackingResponse>?>>

    fun getCompanies(): List<CompaniesImgUrl>?

    fun getOtherCompanies(): List<CompaniesImgUrl>?

    fun getAllCompanies(fileName: String): Companies

    fun getHomeImages(): Flow<State<List<HomeImage>?>>

    fun makeOrder(order: JsonElement): Flow<State<OrderResponse?>>

    suspend fun insertCartItem(CartItem: CartItem)

    suspend fun insertWishItem(WishItem: WishItem)

    suspend fun checkCartItemExists(itemId: String): Boolean

    suspend fun checkWishItemExists(itemId: String): Boolean

    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double)

    fun getCartProducts(): Flow<List<CartItem>>

    fun getTotalPrice(): Flow<Double>

    fun getOldTotalPrice(): Flow<Double>

    fun getPiecesNumber(): Flow<Int>

    suspend fun getCartItemById(id: String): CartItem?

    suspend fun deleteItemById(id: String)

    suspend fun deleteWishItemById(id: String?)

    fun getWishedProducts(): Flow<List<WishItem>>


}