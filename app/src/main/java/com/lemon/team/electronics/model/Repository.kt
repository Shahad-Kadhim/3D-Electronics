package com.lemon.team.electronics.model

import android.annotation.SuppressLint
import com.google.gson.*
import com.lemon.team.electronics.model.data.*
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase
import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.local.CompaniesImgUrl
import com.lemon.team.electronics.model.order.OrderedProduct
import com.lemon.team.electronics.model.orderResponse.OrderResponse
import com.lemon.team.electronics.model.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.model.response.*
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
object Repository{

    private val dao = ProductsItemsDatabase.getInstanceWithContext().productsDao()
    private val localData: LocalData = LocalData(Gson())

    fun getCategories(): Flow<State<List<CategoryResponse>?>> =
        wrapWithFlow { API.apiService.getCategories() }


    fun getProductsByCategoryId(
        categoryId: String,
        page: Int,
        sortBy: String = Constants.SORT_BY_CREATED_DATE
    ): Flow<State<ProductsResponse?>> =
        wrapWithFlow { API.apiService.getProductsByCategoryId(categoryId, page, sortBy) }


    fun getProductByName(
        productName: String,
        page: Int,
        sortBy: String = Constants.SORT_BY_CREATED_DATE
    ): Flow<State<ProductsResponse?>> =
        wrapWithFlow { API.apiService.getProductByName(productName, page, sortBy) }


    fun getRecommendedProducts(): Flow<State<List<Product>?>> =
        wrapWithFlow { API.apiService.getRecommendedProducts() }


    fun getProductById(productId: String): Flow<State<Product?>> =
        wrapWithFlow { API.apiService.getProductById(productId) }


    // this function will be rewritten after create database
    fun getProductsInCart(): Flow<State<ProductsResponse?>> =
        wrapWithFlow {
            API.apiService
                .getProductsByCategoryId(
                    categoryId = CategoriesId.PC_SPEAKER,
                    page = Constants.PAGE_NUMBER_ZERO,
                    sortBy = Constants.SORT_BY_CREATED_DATE
                )
        }

    suspend fun getOrderedProducts(): List<OrderedProduct> {
        return dao.getCartItems().toOrderedProduct()
    }

    suspend fun clearCart() =
        dao.deleteCartItems()


    fun trackOrder(phoneNumber: String): Flow<State<List<OrderTrackingResponse>?>> =
        wrapWithFlow { API.apiService.trackOrder(phoneNumber) }



    fun getCompanies(): List<CompaniesImgUrl>? =
        getAllCompanies(Constants.COMPANY_FILE_NAME).companiesImgUrl
    

    fun getOtherCompanies(): List<CompaniesImgUrl>? =
        getAllCompanies(Constants.COMPANY_FILE_NAME).otherCompaniesImgUrl


    private fun getAllCompanies(fileName: String) =
        localData.getCompanies(fileName)


    fun getHomeImages(): Flow<State<List<HomeImage>?>> =
        wrapWithFlow { API.apiService.getHomeImages() }

    fun makeOrder(order: JsonElement): Flow<State<OrderResponse?>> =
        wrapWithFlow { API.apiService.makeOrder(order) }


    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> {
        return flow {
            emit(State.Loading)
            try {
                emit(checkIsSuccessful(function()))
            }catch (e: Exception) {
                emit(State.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkIsSuccessful(response: Response<T>): State<T?> =
        if (response.isSuccessful) {
            State.Success(response.body())
        }
        else {
            State.Error(response.message())
        }


    suspend fun insertCartItem(CartItem: CartItem) =
        dao.insertCartItem(CartItem)

    suspend fun insertWishItem(WishItem: WishItem) =
        dao.insertWishItem(WishItem)

    suspend fun checkCartItemExists(itemId: String) =
        dao.isCartItemExists(itemId)

    suspend fun checkWishItemExists(itemId: String) =
        dao.isWishItemExists(itemId)

    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double) =
        dao.updateCartItem(itemId, pieces, price)

    fun getCartProducts() =
        dao.getAllCartItems()

    fun getTotalPrice() =
        dao.getTotalPrice()

    fun getOldTotalPrice() =
        dao.getOldTotalPrice()

    fun getPiecesNumber() =
        dao.getPiecesNumber()

    suspend fun getCartItemById(id: String) =
        dao.getICartItemByID(id)

    suspend fun deleteItemById(id: String) =
        dao.deleteItemById(id)

    suspend fun deleteWishItemById(id: String?) =
        dao.deleteWishItemById(id)

    fun getWishedProducts() =
        dao.getAllWishItems()

}







