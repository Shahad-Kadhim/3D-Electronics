package com.lemon.team.electronics.ui.cart

import androidx.lifecycle.*
import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ElectronicRepository
): BaseViewModel() ,CartInteractionListener {

    val cartItems = repository.getCartProducts().asLiveData()
    val totalPrice = repository.getTotalPrice().asLiveData()
    val oldTotalPrice = repository.getOldTotalPrice().asLiveData()
    val piecesNumber = repository.getPiecesNumber().asLiveData()


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
            repository.deleteItemById(productId)
        }
    }


    fun onClickClearAlCart(){
        viewModelScope.launch {
            repository.clearCart()
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