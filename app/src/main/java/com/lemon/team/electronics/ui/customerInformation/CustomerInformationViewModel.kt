package com.lemon.team.electronics.ui.customerInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.model.order.OrderRequest
import com.lemon.team.electronics.model.order.OrderedProduct
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.DataClassParser
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CustomerInformationViewModel : BaseViewModel() {

    val fullName = MutableLiveData<String>()
    val companyName = MutableLiveData<String>()
    val regionName = MutableLiveData<String>()
    val nearestPoint = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val governorate = MutableLiveData<String>()

    fun onSubmitClicked() {
        viewModelScope.launch {
            val order = initOrder()
            val parsedOrder = parseOrder(order)
            makeOrder(parsedOrder)
        }
    }

    private fun parseOrder(order: OrderRequest): JsonElement {
        return DataClassParser.parseToJson(order)
    }

    private suspend fun initOrder(): OrderRequest {
        return OrderRequest(
            name = fullName.value ?: "",
            companyName = companyName.value ?: "",
            regionName = regionName.value ?: "",
            nearestPoint = nearestPoint.value ?: "",
            email = email.value ?: "",
            mobileNumber = phoneNumber.value ?: "",
            notes = notes.value ?: "",
            governorate = governorate.value,
            orderedProducts = Repository.getOrderedProducts()
        )
    }

    private fun makeOrder(order: JsonElement) {
        collectResponse(
            Repository.makeOrder(order)
        ) {
            //write your code here to handle what's happen with order response
        }

    }
}