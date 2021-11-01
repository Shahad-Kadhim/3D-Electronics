package com.lemon.team.electronics.model

import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.response.about.CompaniesImgUrl
import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.ProductsInCategoryResponse
import com.lemon.team.electronics.model.response.recommended.RecommendedProductsResponse
import com.lemon.team.electronics.model.response.search.SearchResponse
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*
import retrofit2.Response

object Repository{

    fun getCategories()
            : Flow<State<CategoriesResponse?>> =
        wrapWithFlow { API.apiService.getCategories() }


    fun getProductsByCategoryId(categoryId: String, page: Int, sortBy: String = "createdAt")
            : Flow<State<ProductsInCategoryResponse?>> =
        wrapWithFlow { API.apiService.getProductsByCategoryId(categoryId, page, sortBy) }


    fun getProductByName(productName: String, page: Int, sortBy: String = "createdAt" )
            :Flow<State<SearchResponse?>> =
        wrapWithFlow { API.apiService.getProductByName(productName, page, sortBy) }


    fun getRecommendedProducts()
            : Flow<State<RecommendedProductsResponse?>> =
        wrapWithFlow { API.apiService.getRecommendedProducts() }


    fun getProductById(productId: String)
            :Flow<State<ProductResponse?>> =
        wrapWithFlow { API.apiService.getProductById(productId) }


    fun getWishedProducts()
            : Flow<State<ProductsInCategoryResponse?>> =
        wrapWithFlow { API.apiService
            .getProductsByCategoryId(
                categoryId = "54653fdb-db67-4e72-8840-1d842e3c4f04",
                page = Constants.PAGE_NUMBER,
                sortBy = "createdAt"
            )
        }



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


    fun getVendors(): List<CompaniesImgUrl>? =
        JsonParse.COMPANIES_LOGOS.companiesImgUrl

    fun getOtherVendors(): List<CompaniesImgUrl>? =
        JsonParse.COMPANIES_LOGOS.otherCompaniesImgUrl

}







