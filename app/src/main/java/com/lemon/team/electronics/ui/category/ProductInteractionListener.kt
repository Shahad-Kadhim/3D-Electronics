package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface ProductInteractionListener: BaseInteractionListener {
    fun onClickProduct(productId : String)
    fun onClickHeart(productId : String)
}