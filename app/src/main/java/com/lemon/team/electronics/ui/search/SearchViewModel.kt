package com.lemon.team.electronics.ui.search

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.remote.response.ProductsResponse
import com.lemon.team.electronics.ui.category.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel(), ProductInteractionListener {

    val searchName = MutableLiveData<String>()
    var searchResult = MutableLiveData<State<ProductsResponse?>>()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    lateinit var categoryId: String
    private val scroll = MutableStateFlow(0)
    private val _loading = MutableLiveData<State<ProductsResponse?>>()
    val loading: LiveData<State<ProductsResponse?>> = _loading

    fun onclickSearch() {
        scroll.tryEmit(0)
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
        getProductsInCurrentPage { state ->
            when (state) {
                is State.Error, State.Loading -> {
                    _loading.postValue(state)
                }
                is State.Success -> {
                    state.toData()?.products?.apply {
                        this.addAll(0, searchResult.value?.toData()?.products ?: mutableListOf())
                    }
                    searchResult.postValue(state)
                    _loading.postValue(state)
                }
            }
        }
    }

    private fun getProductsInCurrentPage(onResponse: (State<ProductsResponse?>) -> Unit) {
        searchName.value?.trim()?.let { nameYouSearch ->
            collectResponse(
                repository.getProductByName(nameYouSearch, scroll.value), onResponse)
        }
    }

    fun onclickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    fun onClickClear() {
       searchName.postValue("")
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }


}