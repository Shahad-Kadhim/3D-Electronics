package com.lemon.team.electronics.ui.cart

import androidx.lifecycle.*
import com.lemon.team.electronics.model.repository.DatabaseRepository
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event


class CartViewModel : BaseViewModel() , CartInteractionListener {

    val cartItems = DatabaseRepository.getAllProducts().asLiveData()


    val totalPrice = DatabaseRepository.getTotalPrice().asLiveData()




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