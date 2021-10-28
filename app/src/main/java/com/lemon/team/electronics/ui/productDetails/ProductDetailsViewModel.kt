package com.lemon.team.electronics.ui.productDetails

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

    private fun getDetailsProduct(productId: String) {
        viewModelScope.launch {
            Repository.getProductById(productId)
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect { detailsProduct.postValue(it) }
        }
    }

}
