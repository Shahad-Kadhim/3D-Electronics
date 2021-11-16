package com.lemon.team.electronics.ui.wishList

import androidx.lifecycle.*
import com.lemon.team.electronics.model.repository.Repository
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.launch

class WishListViewModel : BaseViewModel() , WishInteractionListener {

    val wishListItems = Repository.getWishedProducts().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent : LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent

    private var _clickAdd = MutableLiveData<Event<Boolean>>()
    val clickAdd: LiveData<Event<Boolean>> = _clickAdd



    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickHeart(productId: String) {

    }

    override fun onclickAddToCart(Product: Product){
        _clickAdd.postValue(Event(true))
        viewModelScope.launch {
            Repository.insertProduct(setItem(Product))
        }
    }

    fun setItem(Product: Product) =
        Product.convertToItem(Constants.CART, 1)

    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

}