package com.lemon.team.electronics.ui.wishList

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class WishListRecyclerAdapter(items: List<Content>, listener: ProductInteractionListener)
    : BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int = R.layout.item_wish_product

}