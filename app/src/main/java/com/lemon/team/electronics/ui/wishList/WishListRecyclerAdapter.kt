package com.lemon.team.electronics.ui.wishList

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.data.CartItem
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class WishListRecyclerAdapter(items: List<CartItem>, listener: WishInteractionListener)
    : BaseRecyclerAdapter<CartItem>(items, listener) {

    override val layoutId: Int = R.layout.item_wish_product

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].id == (newItems[newItemPosition] as CartItem).id

}

interface WishInteractionListener: BaseInteractionListener {
    fun onClickProduct(productId: String)
    fun onClickHeart(productId: String)
    fun onclickAddToCart(productId: Product)
}