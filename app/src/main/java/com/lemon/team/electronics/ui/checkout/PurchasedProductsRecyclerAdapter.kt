package com.lemon.team.electronics.ui.checkout

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class PurchasedProductsRecyclerAdapter(items: List<Product>, listener: BaseInteractionListener) :
    BaseRecyclerAdapter<Product>(items, listener) {

    override val layoutId: Int = R.layout.item_checkout

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ) = getItems()[oldItemPosition].id == (newItems[newItemPosition] as Product).id

}