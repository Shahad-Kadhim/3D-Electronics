package com.lemon.team.electronics.ui.checkout

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.local.CartItem
import com.lemon.team.electronics.ui.base.*

class PurchasedProductsRecyclerAdapter(
    items: List<CartItem>,
    listener: BaseInteractionListener,
) :
    BaseRecyclerAdapter<CartItem>(items, listener) {

    override val layoutId: Int = R.layout.item_checkout

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ) = getItems()[oldItemPosition].id == (newItems[newItemPosition] as CartItem).id

}