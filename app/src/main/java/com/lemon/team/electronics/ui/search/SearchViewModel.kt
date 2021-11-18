package com.lemon.team.electronics.ui.search

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*

class SearchViewModel: BaseViewModel(), ProductInteractionListener {

    val searchName = MutableLiveData<String>()
    var searchResult = MutableLiveData<State<ProductsResponse?>>()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent :LiveData<Event<String>>  = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: Product) {
        // write code after create database
    }

    fun onclickSearch() {
        searchName.value?.let { nameYouSearch ->
            collectResponse(
                Repository.getProductByName(nameYouSearch, Constants.PAGE_NUMBER_ZERO)) { product ->
                searchResult.postValue(product)
            }
        }
    }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }

}