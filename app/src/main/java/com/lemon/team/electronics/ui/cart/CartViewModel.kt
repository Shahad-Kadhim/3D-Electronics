package com.lemon.team.electronics.ui.cart

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import kotlinx.coroutines.launch


class CartViewModel : BaseViewModel() , CartInteractionListener {

    val cartItems = Repository.getCartProducts().asLiveData()
    val totalPrice = Repository.getTotalPrice().asLiveData()
    val oldTotalPrice = Repository.getOldTotalPrice().asLiveData()
    val piecesNumber = Repository.getPiecesNumber().asLiveData()

    private val _clickPayNowEvent = MutableLiveData<Event<Boolean>>()
    var clickPayNowEvent: LiveData<Event<Boolean>> = _clickPayNowEvent

    private val _clickItemEvent = MutableLiveData<Event<String>>()
    var clickItemEvent: LiveData<Event<String>> = _clickItemEvent

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    private val _clickCheckoutEvent = MutableLiveData<Event<Boolean>>()
    var clickCheckoutEvent: LiveData<Event<Boolean>> = _clickCheckoutEvent

    override fun onClickProduct(productId: String) {
        _clickItemEvent.postValue(Event(productId))
    }

    override fun onClickDelete(productId: String){
        viewModelScope.launch {
            Repository.deleteItemById(productId)
        }
    }

    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

    fun onClickCheckout() {
        _clickCheckoutEvent.postValue(Event(true))
    }

    fun onClickPayNow() {
        _clickPayNowEvent.postValue(Event(true))
    }


}