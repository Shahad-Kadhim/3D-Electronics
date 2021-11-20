package com.lemon.team.electronics.ui.orderTracking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderTrackingViewModel: BaseViewModel(), OrderInteractionListener {

    val phoneNumber = MutableLiveData<String>()

    private val _orderTrackingResponse = MutableLiveData<State<List<OrderTrackingResponse>?>>()
    val orderTrackingResponse: LiveData<State<List<OrderTrackingResponse>?>> = _orderTrackingResponse

    fun trackOrder() {
        viewModelScope.launch {
            Repository.trackOrder(phoneNumber.value).collect { orderTrackingResponse->
                _orderTrackingResponse.postValue(orderTrackingResponse)
            }
        }
        Log.i("kkk", _orderTrackingResponse.value.toString())
    }

}