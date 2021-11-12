package com.lemon.team.electronics.ui.cart

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CartRecyclerAdapter(items: List<Product>, listener: CartInteractionListener)
    : BaseRecyclerAdapter<Product>(items, listener) {

    override val layoutId: Int = R.layout.item_product_in_cart

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id

}

interface CartInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)

}