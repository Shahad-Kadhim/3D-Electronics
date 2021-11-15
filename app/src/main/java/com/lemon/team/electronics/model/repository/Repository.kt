package com.lemon.team.electronics.model.repository

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.local.CompaniesImgUrl
import com.lemon.team.electronics.model.response.*
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
object Repository{

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


    // this function will be rewritten after create database
    fun getProductsInCart(): Flow<State<ProductsResponse?>> =
        wrapWithFlow { API.apiService
            .getProductsByCategoryId(
                categoryId = CategoriesId.PC_SPEAKER,
                page = Constants.PAGE_NUMBER_ZERO,
                sortBy = Constants.SORT_BY_CREATED_DATE
            )
        }


    // this function gets the total price of the products in the cart from the database
    fun getTotalPrice() = 500


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


}






