package com.lemon.team.electronics.ui.wishList

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface WishListInteractionListener: BaseInteractionListener {

    fun onClickProduct(productId: String)

}