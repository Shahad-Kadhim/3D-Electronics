package com.lemon.team.electronics.model

import com.lemon.team.electronics.model.network.API
import com.lemon.team.electronics.model.response.about.Companies
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


    // this function will be rewritten after create database
    fun getWishedProducts()
            : Flow<State<ProductsInCategoryResponse?>> =
        wrapWithFlow { API.apiService
            .getProductsByCategoryId(
                categoryId = "54653fdb-db67-4e72-8840-1d842e3c4f04",
                page = Constants.PAGE_NUMBER,
                sortBy = "createdAt"
            )
        }

    // this function will be rewritten after create database
    fun getProductsInCart()
            : Flow<State<ProductsInCategoryResponse?>> =
        wrapWithFlow { API.apiService
            .getProductsByCategoryId(
                categoryId = "54653fdb-db67-4e72-8840-1d842e3c4f04",
                page = Constants.PAGE_NUMBER,
                sortBy = "createdAt"
            )
        }

    // this function gets the total price of the products in the cart from the database
    fun getTotalPrice(): Int {
        return 500
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


    fun getVendors(): List<Companies> = listOf(
        Companies( "https://static.vecteezy.com/system/resources/previews/001/481/849/large_2x/pet-adoption-with-cute-little-cats-vector.jpg"),
        Companies( "https://static.vecteezy.com/system/resources/previews/002/657/069/large_2x/cute-cat-take-bath-cartoon-character-free-vector.jpg"),
        Companies( "https://static.vecteezy.com/system/resources/previews/002/613/769/non_2x/pet-shop-veterinary-with-food-animals-vector.jpg"),
        Companies( "https://static.vecteezy.com/system/resources/previews/000/152/772/large_2x/free-gerbil-vector.png"),
    )

    fun getOtherVendors(): List<Companies> = listOf(
        Companies( "https://image.freepik.com/free-vector/cute-shiba-inu-design-with-mask-vaccine_454510-35.jpg"),
        Companies("https://image.freepik.com/free-vector/cute-cat-holding-fish-balloon_138676-1193.jpg"),
        Companies( "https://static.vecteezy.com/system/resources/previews/000/129/025/large_2x/vector-papillon-dog.jpg"),
        Companies( "https://static.vecteezy.com/system/resources/previews/001/963/388/non_2x/couple-owl-and-bird-house-vector.jpg"),
    )

}







