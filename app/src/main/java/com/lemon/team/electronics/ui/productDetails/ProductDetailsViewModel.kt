package com.lemon.team.electronics.ui.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductDetailsViewModel : BaseViewModel() {

    var detailsProduct = MutableLiveData<State<ProductResponse?>>()

    fun getDetailsProduct() {
        viewModelScope.launch {
            Repository.getProductById("4720df27-ac48-4f90-8235-b443dc8d45c1")
                .catch {  }
                .collect {
                    detailsProduct.value = it
                }
        }
    }

}
