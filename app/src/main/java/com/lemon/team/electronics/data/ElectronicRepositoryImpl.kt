package com.lemon.team.electronics.data

import android.annotation.SuppressLint
import com.google.gson.*
import com.lemon.team.electronics.data.local.database.ProductsItemsDao
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.data.local.CompaniesImgUrl
import com.lemon.team.electronics.data.local.WishItem
import com.lemon.team.electronics.data.remote.ProductService
import com.lemon.team.electronics.data.remote.order.OrderedProduct
import com.lemon.team.electronics.data.remote.orderResponse.OrderResponse
import com.lemon.team.electronics.data.remote.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.data.remote.response.*
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class ElectronicRepositoryImpl @Inject constructor(
    private val dao: ProductsItemsDao,
    private val api: ProductService,
    private val localData: LocalData
): ElectronicRepository {

    override fun getCategories(): Flow<State<List<CategoryResponse>?>> =
        wrapWithFlow { api.getCategories() }

    override fun getProductsByCategoryId(
        categoryId: String,
        page: Int,
        sortBy: String
    ): Flow<State<ProductsResponse?>> =
        wrapWithFlow { api.getProductsByCategoryId(categoryId, page, sortBy) }


    override fun getProductByName(
        productName: String,
        page: Int,
        sortBy: String
    ): Flow<State<ProductsResponse?>> =
        wrapWithFlow { api.getProductByName(productName, page, sortBy) }


    override fun getRecommendedProducts(): Flow<State<List<Product>?>> =
        wrapWithFlow { api.getRecommendedProducts() }


    override fun getProductById(productId: String): Flow<State<Product?>> =
        wrapWithFlow { api.getProductById(productId) }


    // this function will be rewritten after create database
    override fun getProductsInCart(): Flow<State<ProductsResponse?>> =
        wrapWithFlow {
            api
                .getProductsByCategoryId(
                    categoryId = CategoriesId.PC_SPEAKER,
                    page = Constants.PAGE_NUMBER_ZERO,
                    sortBy = Constants.SORT_BY_CREATED_DATE
                )
        }

    override suspend fun getOrderedProducts(): List<OrderedProduct> {
        return dao.getCartItems().toOrderedProduct()
    }

    override suspend fun clearCart() =
        dao.deleteCartItems()


    override fun trackOrder(phoneNumber: String): Flow<State<List<OrderTrackingResponse>?>> =
        wrapWithFlow { api.trackOrder(phoneNumber) }



    override fun getCompanies(): List<CompaniesImgUrl>? =
        getAllCompanies(Constants.COMPANY_FILE_NAME).companiesImgUrl
    

    override fun getOtherCompanies(): List<CompaniesImgUrl>? =
        getAllCompanies(Constants.COMPANY_FILE_NAME).otherCompaniesImgUrl


    override fun getAllCompanies(fileName: String) =
        localData.getCompanies(fileName)


    override fun getHomeImages(): Flow<State<List<HomeImage>?>> =
        wrapWithFlow { api.getHomeImages() }

    override fun makeOrder(order: JsonElement): Flow<State<OrderResponse?>> =
        wrapWithFlow { api.makeOrder(order) }


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


    override suspend fun insertCartItem(CartItem: CartItem) =
        dao.insertCartItem(CartItem)

    override suspend fun insertWishItem(WishItem: WishItem) =
        dao.insertWishItem(WishItem)

    override suspend fun checkCartItemExists(itemId: String) =
        dao.isCartItemExists(itemId)

    override suspend fun checkWishItemExists(itemId: String) =
        dao.isWishItemExists(itemId)

    override suspend fun updateCartItem(itemId: String, pieces: Int, price: Double) =
        dao.updateCartItem(itemId, pieces, price)

    override fun getCartProducts() =
        dao.getAllCartItems()

    override fun getTotalPrice() =
        dao.getTotalPrice()

    override fun getOldTotalPrice() =
        dao.getOldTotalPrice()

    override fun getPiecesNumber() =
        dao.getPiecesNumber()

    override suspend fun getCartItemById(id: String) =
        dao.getICartItemByID(id)

    override suspend fun deleteItemById(id: String) =
        dao.deleteItemById(id)

    override suspend fun deleteWishItemById(id: String?) =
        dao.deleteWishItemById(id)

    override fun getWishedProducts() =
        dao.getAllWishItems()

}







