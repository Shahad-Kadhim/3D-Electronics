package com.lemon.team.electronics.model

import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.response.*
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.*
import retrofit2.Response


fun getCategory()
        : Flow<State<CategoryResponse?>> =
    wrapWithFlow { API.apiService.getCategory() }


fun getProductFromCategory(productId: String, page: Int, sortBy: String)
        :Flow<State<ProductContentResponse?>> =
    wrapWithFlow { API.apiService.getProductFromCategory(productId, page, sortBy) }


fun getProductFromSearchResult(productName: String, page: Int, sortBy: String)
        :Flow<State<ProductSearchResultResponse?>> =
    wrapWithFlow { API.apiService.getProductFromSearchResult(productName, page, sortBy = "createdAt") }


fun getRecommendedProducts()
        : Flow<State<ProductContentResponse?>> =
    wrapWithFlow { API.apiService.getRecommendedProducts() }


fun getProductContent(productId: String)
        :Flow<State<ProductContentResponse?>> =
    wrapWithFlow { API.apiService.getProductContent(productId) }



private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<State<T?>> {

    return flow {
        emit(State.Loading)
        try {
            val responseResult = function()
            if (responseResult.isSuccessful)  State.Success(responseResult.body())
            else State.Error(responseResult.message())
        } catch (e: Exception) {
            State.Error(e.message.toString())
        }
    }
}



