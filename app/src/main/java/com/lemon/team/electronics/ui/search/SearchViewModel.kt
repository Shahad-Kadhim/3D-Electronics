package com.lemon.team.electronics.ui.search

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.MutableStateFlow

class SearchViewModel: BaseViewModel(), ProductInteractionListener {

    val searchName = MutableLiveData<String>()
    var searchResult = MutableLiveData<State<ProductsResponse?>>()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    lateinit var categoryId: String
    private val scroll = MutableStateFlow(0)

    fun onclickSearch() {
        getProductsInCurrentPage {
            searchResult.postValue(it)
        }
    }

    fun onScrollLast() {
        searchResult.value?.toData()?.let { products ->
            if (products.hasNewPage(scroll.value)) {
                scroll.tryEmit(++scroll.value)
                requestMoreProducts()
            }
        }
    }

    private fun requestMoreProducts() {
        getProductsInCurrentPage { oldState ->
            oldState.add(searchResult.getProductsOrEmptyList()) { newState ->
                searchResult.postValue(newState)
            }
        }
    }

    private fun getProductsInCurrentPage(onResponse: (State<ProductsResponse?>) -> Unit) {
        searchName.value?.let { nameYouSearch ->
            collectResponse(
                Repository.getProductByName(nameYouSearch, scroll.value), onResponse)
        }
    }

    fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) {
        // write code after create database
    }

}