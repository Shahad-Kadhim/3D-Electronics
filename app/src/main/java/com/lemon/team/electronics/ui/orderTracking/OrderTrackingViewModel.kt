package com.lemon.team.electronics.ui.orderTracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.data.Repository
import com.lemon.team.electronics.data.remote.orderTracking.OrderTrackingResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderTrackingViewModel @Inject constructor(
    private val repository: Repository
): BaseViewModel(), OrderInteractionListener {

    val phoneNumber = MutableLiveData<String>()

    private val _orderTrackingResponse = MutableLiveData<State<List<OrderTrackingResponse>?>>()
    val orderTrackingResponse: LiveData<State<List<OrderTrackingResponse>?>> =
        _orderTrackingResponse

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    var clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    fun trackOrder() {
        viewModelScope.launch {
            phoneNumber.value?.takeIf { it.isNotBlank() }?.let { phoneNumber ->
                repository.trackOrder(phoneNumber).collect { orderTrackingResponse ->
                    _orderTrackingResponse.postValue(orderTrackingResponse)
                }
            }
        }
    }

    fun onClickBack() {
        _clickBackEvent.postValue(Event(true))
    }

}