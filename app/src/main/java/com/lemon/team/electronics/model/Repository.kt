package com.lemon.team.electronics.model

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.lemon.team.electronics.model.data.CartItem
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase
import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.local.CompaniesImgUrl
import com.lemon.team.electronics.model.order.OrderedProduct
import com.lemon.team.electronics.model.orderResponse.OrderResponse
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


    //this function will be rewritten after create database
    fun getOrderedProducts(): List<OrderedProduct>{
        return listOf(
            OrderedProduct(
                productCount = 1,
                productId = "a6a7da21-ff30-466a-b633-365b94685a8f"
            )
        )
    }



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



    suspend fun insertProduct(CartItem: CartItem) =
        dao.insert(CartItem)

    suspend fun checkItemExists(itemId: String) =
        dao.isItemExists(itemId)

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

    suspend fun getItemById(id: String) =
        dao.getItemByID(id)

    suspend fun deleteItemById(id: String) =
        dao.deleteItemById(id)

    fun getWishedProducts() =
        dao.getAllWishItems()

}







