package com.lemon.team.electronics.ui.cart

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CartInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)

}