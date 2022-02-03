package com.lemon.team.electronics.ui.category

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.remote.response.ProductsResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel(), ProductInteractionListener{

    private val _categoryItems = MutableLiveData<State<ProductsResponse?>>()
    var items: LiveData<State<ProductsResponse?>> = _categoryItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val scroll = MutableStateFlow(0)
    private val _loading = MutableLiveData<State<ProductsResponse?>>()
    val loading: LiveData<State<ProductsResponse?>> = _loading

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
        getProductsInCurrentPage { state ->
            when (state) {
                is State.Error, State.Loading -> {
                    _loading.postValue(state)
                }
                is State.Success -> {
                    state.toData()?.products?.apply {
                        this.addAll(0, _categoryItems.value?.toData()?.products ?: mutableListOf())
                    }
                    _categoryItems.postValue(state)
                    _loading.postValue(state)
                }
            }
        }
    }

    private fun getProductsInCurrentPage(onResponse: (State<ProductsResponse?>) -> Unit) {
        collectResponse(
            repository.getProductsByCategoryId(categoryId, scroll.value), onResponse)
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    fun onClickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}
