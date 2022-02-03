package com.lemon.team.electronics.ui.wishList

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.local.WishItem
import com.lemon.team.electronics.ui.base.*


class WishListRecyclerAdapter(items: List<WishItem>, listener: WishInteractionListener)
    : BaseRecyclerAdapter<WishItem>(items, listener) {

    override val layoutId: Int = R.layout.item_wish_product

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as WishItem).id

}

interface WishInteractionListener: BaseInteractionListener {
    fun onClickProduct(productId: String)
    fun onclickAddToCart(productId: WishItem)
    fun onclickHeart(productId: String)

}