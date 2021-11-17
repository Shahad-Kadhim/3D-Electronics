package com.lemon.team.electronics.ui.orderTracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderTrackingViewModel: BaseViewModel() {

    val phoneNumber = MutableLiveData<String>()

    private val _orderTrackingResponse = MutableLiveData<State<OrderTrackingResponse?>>()
    val orderTrackingResponse: LiveData<State<OrderTrackingResponse?>> = _orderTrackingResponse

    fun trackOrder() {
        viewModelScope.launch {
            Repository.trackOrder(phoneNumber.value).collect { orderTrackingResponse ->
                _orderTrackingResponse.postValue(orderTrackingResponse)
            }
        }
    }
}