package com.lemon.team.electronics.ui.productDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProductDetailsViewModel : BaseViewModel() {

    var detailsProduct = MutableLiveData<State<ProductResponse?>>()

    init {
        getDetailsProduct()
    }

    private fun getDetailsProduct() {
        viewModelScope.launch {
            Repository.getProductById("4720df27-ac48-4f90-8235-b443dc8d45c1")
                .flowOn(Dispatchers.IO)
                .catch { Log.i("hhhhhhhhhh" , "gggg") }
                .collect {
                    Log.i("hhhhhhhhhh" , it.toData().toString())
                    detailsProduct.postValue(it)
                }
        }
    }


}
