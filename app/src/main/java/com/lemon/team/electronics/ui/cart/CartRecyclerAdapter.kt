package com.lemon.team.electronics.ui.cart

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.data.Item
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CartRecyclerAdapter(items: List<Item>, listener: CartInteractionListener)
    : BaseRecyclerAdapter<Item>(items, listener) {

    override val layoutId: Int = R.layout.item_product_in_cart

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as Item).id

}

interface CartInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)

}