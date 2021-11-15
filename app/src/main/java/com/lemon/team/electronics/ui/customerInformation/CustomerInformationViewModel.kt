package com.lemon.team.electronics.ui.customerInformation

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import com.lemon.team.electronics.model.order.OrderRequest
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.Constants
import com.lemon.team.electronics.util.DataClassParser

class CustomerInformationViewModel: BaseViewModel(), AdapterView.OnItemSelectedListener {

    val fullName = MutableLiveData<String>()
    val companyName = MutableLiveData<String>()
    val regionName = MutableLiveData<String>()
    val nearestPoint = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val city = MutableLiveData<String>()

    fun onSubmitClicked() {
        val order = OrderRequest(
            name = fullName.value ?: "",
            companyName = companyName.value ?: "",
            regionName = regionName.value ?: "",
            nearestPoint = nearestPoint.value ?: "",
            email = email.value ?: "",
            mobileNumber = phoneNumber.value ?: "",
            notes = notes.value ?: "",
            city = city.value,
            orderedProducts = emptyList()
        )

        val orderJson = DataClassParser.parseToJson(order)
        Log.v(Constants.LOG_TAG, orderJson)

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        city.postValue(p0?.selectedItem.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}