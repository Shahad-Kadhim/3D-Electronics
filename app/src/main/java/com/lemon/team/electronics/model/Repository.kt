package com.lemon.team.electronics.model

import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.response.*
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.*
import retrofit2.Response

object Repository{

    fun getCategories()
            : Flow<State<List<CategoryResponse>?>> =
        wrapWithFlow { API.apiService.getCategories() }


    fun getProductsByCategoryId(categoryId: String, page: Int, sortBy: String = "createdAt")
            : Flow<State<List<ProductContentResponse>?>> =
        wrapWithFlow { API.apiService.getProductsByCategoryId(categoryId, page, sortBy) }


    fun getProductByName(productName: String, page: Int, sortBy: String = "createdAt" )
            :Flow<State<ProductSearchResultResponse?>> =
        wrapWithFlow { API.apiService.getProductByName(productName, page, sortBy) }


    fun getRecommendedProducts()
            : Flow<State<List<ProductContentResponse>?>> =
        wrapWithFlow { API.apiService.getRecommendedProducts() }


    fun getProductById(productId: String)
            :Flow<State<ProductContentResponse?>> =
        wrapWithFlow { API.apiService.getProductById(productId) }


    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> {
        return flow {
            emit(State.Loading)
            emit(checkIsSuccessful(function()))
        }
    }

    private fun <T> checkIsSuccessful(response: Response<T>): State<T?> {
        return try {
            if (response.isSuccessful) { State.Success(response.body()) }
            else State.Error(response.message())
        } catch (e: Exception) {
            State.Error(e.message.toString())
        }
    }

}







