package com.lemon.team.electronics.ui.checkout

import androidx.lifecycle.*
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.*
import com.lemon.team.electronics.util.*

class CheckoutViewModel : BaseViewModel(), BaseInteractionListener {

    val products = Repository.getProductsInCart().asLiveData()

    val numberOfProducts = Transformations.map(products) {
        if (it is State.Success) {
            it.data?.products?.size
        } else {
            0
        }
    }

    val totalPrice: LiveData<Double> =
        Transformations.map(products) { state ->
            state.toData()?.products?.map { it.price }?.sum()
        }

}


