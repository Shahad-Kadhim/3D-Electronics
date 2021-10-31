package com.lemon.team.electronics.ui.wishList

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*

class WishListViewModel : BaseViewModel() , WishListInteractionListener {

    val wishListItems = Repository.getWishedProducts().asLiveData()

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent : LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent :LiveData<Event<Boolean>>  = _clickBackEvent


    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

}