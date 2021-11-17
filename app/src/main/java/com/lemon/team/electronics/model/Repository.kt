package com.lemon.team.electronics.model

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.model.data.database.ProductsItemsDatabase
import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.local.CompaniesImgUrl
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
    fun getWishedProducts(): Flow<State<ProductsResponse?>> =
        wrapWithFlow { API.apiService
            .getProductsByCategoryId(
                categoryId = CategoriesId.MONITORS,
                page = Constants.PAGE_NUMBER_ZERO,
                sortBy = Constants.SORT_BY_CREATED_DATE
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



    suspend fun insertProduct(ProductItem: ProductItem) =
        dao.insert(ProductItem)

    suspend fun checkItemExists(itemId: String) =
        dao.isItemExists(itemId)

    suspend fun updateCartItem(itemId: String, pieces: Int, price: Double) =
        dao.updateCartItem(itemId, pieces, price)

    fun getAllProducts() =
        dao.getAllCartItems()

    fun getTotalPrice() =
        dao.getTotalPrice()

    fun getOldTotalPrice() =
        dao.getOldTotalPrice()

    fun getPiecesNumber() =
        dao.getPiecesNumber()

    fun getItemById(id: String) =
        dao.getItemByID(id)

}







