package com.lemon.team.electronics.ui.cart

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.data.ProductItem
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CartRecyclerAdapter(productItems: List<ProductItem>, listener: CartInteractionListener)
    : BaseRecyclerAdapter<ProductItem>(productItems, listener) {

    override val layoutId: Int = R.layout.item_product_in_cart

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as ProductItem).id

    override fun areContentSame(
        oldPosition: Int,
        newPosition: Int,
        newList: List<ProductItem>,
    ) =
        getItems()[oldPosition].pieces == newList[newPosition].pieces

}

interface CartInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)
    fun onClickDelete(productId: String)

}