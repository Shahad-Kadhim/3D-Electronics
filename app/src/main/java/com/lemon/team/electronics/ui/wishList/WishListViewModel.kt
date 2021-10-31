package com.lemon.team.electronics.ui.wishList

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.productsByCategoryId.ProductsInCategoryResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class WishListViewModel : BaseViewModel() , WishListInteractionListener {

    private val _wishListItems = MutableLiveData<State<ProductsInCategoryResponse?>>()
    var wishListItems : LiveData<State<ProductsInCategoryResponse?>> = _wishListItems

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent : LiveData<Event<String>> = _clickItemEvent


    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    fun getWishedProducts(categoryId: String){
        viewModelScope.launch {
            Repository.getWishedProducts("54653fdb-db67-4e72-8840-1d842e3c4f04" ,Constants.PAGE_NUMBER)
                .flowOn(Dispatchers.IO)
                .collect {
                    _wishListItems.postValue(it)
                }
        }
    }

    fun onclickBack(){
        _clickBackEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) =
        _clickItemEvent.postValue(Event(productId))

}