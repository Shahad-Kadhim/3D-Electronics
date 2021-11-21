package com.lemon.team.electronics.ui.orderTracking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderTrackingViewModel: BaseViewModel(), OrderInteractionListener {

    val phoneNumber = MutableLiveData<String>()

    private val _orderTrackingResponse = MutableLiveData<State<List<OrderTrackingResponse>?>>()
    val orderTrackingResponse: LiveData<State<List<OrderTrackingResponse>?>> =
        _orderTrackingResponse

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    fun trackOrder() {
        viewModelScope.launch {
            phoneNumber.value?.takeIf { it.isNotBlank() }?.let { phoneNumber ->
                Repository.trackOrder(phoneNumber).collect { orderTrackingResponse ->
                    _orderTrackingResponse.postValue(orderTrackingResponse)
                }
            }
        }
    }

    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

}