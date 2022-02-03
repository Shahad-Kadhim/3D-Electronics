package com.lemon.team.electronics.ui.customerInformation

import androidx.lifecycle.*
import com.google.gson.JsonElement
import com.lemon.team.electronics.data.Repository
import com.lemon.team.electronics.data.remote.order.*
import com.lemon.team.electronics.data.remote.orderResponse.OrderResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerInformationViewModel @Inject constructor(
    private val repository: Repository,
    private val dataClassParser: DataClassParser
): BaseViewModel() {

    private val _orderResponse = MutableLiveData<Event<State<OrderResponse?>>>()
    val orderResponse: LiveData<Event<State<OrderResponse?>>> = _orderResponse

    private val _clickBackEvent = MutableLiveData<Event<Boolean>>()
    val clickBackEvent: LiveData<Event<Boolean>> = _clickBackEvent

    val fullName = MutableLiveData<String>()
    val companyName = MutableLiveData<String>()
    val regionName = MutableLiveData<String>()
    val nearestPoint = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val governorate = MutableLiveData<String>()
    var products = emptyList<OrderedProduct>()

    val informationValidation = MediatorLiveData<Boolean>().apply {
        addSource(fullName, this@CustomerInformationViewModel::checkValidation)
        addSource(regionName, this@CustomerInformationViewModel::checkValidation)
        addSource(phoneNumber, this@CustomerInformationViewModel::checkValidation)
    }

    private fun checkValidation(value: String) {
        if (
            fullName.value?.isNotEmpty() == true &&
            regionName.value?.isNotEmpty() == true &&
            phoneNumber.value?.count() == Constants.VALID_NUMBER_OF_DIGIT_OF_PHONE_NUMBER
        ) {
            informationValidation.postValue(true)
        } else informationValidation.postValue(false)
    }

    init {
        viewModelScope.launch {
            products = repository.getOrderedProducts()
        }
    }

    fun onSubmitClicked() {
        val order = initOrder()
        val parsedOrder = parseOrder(order)
        makeOrder(parsedOrder)
    }

    private fun parseOrder(order: OrderRequest): JsonElement {
        return dataClassParser.parseToJson(order)
    }

    private fun initOrder(): OrderRequest {
        return OrderRequest(
            name = fullName.value ?: "",
            companyName = companyName.value ?: "",
            regionName = regionName.value ?: "",
            nearestPoint = nearestPoint.value ?: "",
            email = email.value ?: "",
            mobileNumber = phoneNumber.value ?: "",
            notes = notes.value ?: "",
            governorate = governorate.value,
            orderedProducts = products
        )
    }

    private fun makeOrder(order: JsonElement) {
        collectResponse(
            repository.makeOrder(order)
        ) { orderResponse ->
            _orderResponse.postValue(Event(orderResponse))
        }

    }

    fun clearCartItems() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    fun onClickBack(){
        _clickBackEvent.postValue(Event(true))
    }

}