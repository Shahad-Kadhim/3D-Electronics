package com.lemon.team.electronics.ui.category

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.flow.*

class CategoryViewModel : BaseViewModel(), ProductInteractionListener{

    private val _categoryItems = MutableLiveData<State<ProductsResponse?>>()
    var items: LiveData<State<ProductsResponse?>> = _categoryItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val scroll = MutableStateFlow(0)

    lateinit var categoryId: String

    fun getProductsByCategoryId(categoryId: String) {
        this.categoryId = categoryId
        getProductsInCurrentPage { state ->
            _categoryItems.postValue(state)
        }
    }

    fun onScrollLast() {
        _categoryItems.value?.toData()?.let { category ->
            if (category.hasNewPage(scroll.value)) {
                scroll.tryEmit(++scroll.value)
                requestMoreProducts()
            }
        }
    }


    private fun requestMoreProducts() {
        getProductsInCurrentPage { oldState ->
            oldState.add(_categoryItems.getProductsOrEmptyList()) { newState ->
                _categoryItems.postValue(newState)
            }
        }
    }

    private fun getProductsInCurrentPage(onResponse: (State<ProductsResponse?>) -> Unit) {
        collectResponse(
            Repository.getProductsByCategoryId(categoryId, scroll.value), onResponse)
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) { }

    fun onClickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}
