package com.lemon.team.electronics.ui.customerInformation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.order.OrderRequest
import com.lemon.team.electronics.model.order.OrderedProduct
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.DataClassParser

class CustomerInformationViewModel: BaseViewModel() {

    val fullName = MutableLiveData<String>()
    val companyName = MutableLiveData<String>()
    val regionName = MutableLiveData<String>()
    val nearestPoint = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val governorate = MutableLiveData<String>()

    fun onSubmitClicked() {
        val order = OrderRequest(
            name = fullName.value ?: "",
            companyName = companyName.value ?: "",
            regionName = regionName.value ?: "",
            nearestPoint = nearestPoint.value ?: "",
            email = email.value ?: "",
            mobileNumber = phoneNumber.value ?: "",
            notes = notes.value ?: "",
            governorate = governorate.value,
            orderedProducts = listOf(
                OrderedProduct(
                    productCount = 1,
                    productId = "a6a7da21-ff30-466a-b633-365b94685a8f"
                )
            )

        )

        val orderJson = DataClassParser.parseToJson(order)
        Log.v(Constants.LOG_TAG, orderJson.toString())
        makeOrder(orderJson)
    }

    private fun makeOrder(order: JsonElement) {
        collectResponse(
            Repository.makeOrder(order)){
                Log.v(Constants.LOG_TAG, it.toString())
            }

    }
}