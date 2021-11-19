package com.lemon.team.electronics.ui.customerInformation.orderStatus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Event

class OrderStatusViewModel: BaseViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome : LiveData<Event<Boolean>> = _navigateToHome

    private val _navigateToFollowOrder = MutableLiveData<Event<Boolean>>()
    val navigateToFollowOrder: LiveData<Event<Boolean>> = _navigateToFollowOrder

    fun onDialogButtonClicked() {
        _navigateToHome.postValue(Event(true))
    }

    fun onFollowOrderClicked(){
        _navigateToFollowOrder.postValue(Event(true))
    }
}