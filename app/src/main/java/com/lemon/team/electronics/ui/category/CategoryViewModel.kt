package com.lemon.team.electronics.ui.category

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.ProductsResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel(), ProductInteractionListener{

    private val _categoryItems = MutableLiveData<State<ProductsResponse?>>()
    var items : LiveData<State<ProductsResponse?>> = _categoryItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent :LiveData<Event<String>>  = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    fun getProductsByCategoryId(categoryId: String){
        viewModelScope.launch {
            Repository.getProductsByCategoryId(categoryId, Constants.PAGE_NUMBER_ZERO)
                .flowOn(Dispatchers.IO)
                .collect {
                    _categoryItems.postValue(it)
                }
        }
    }


    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) { }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }
}
