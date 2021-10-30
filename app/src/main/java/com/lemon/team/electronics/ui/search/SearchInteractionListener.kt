package com.lemon.team.electronics.ui.search

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface SearchInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId : String)

}