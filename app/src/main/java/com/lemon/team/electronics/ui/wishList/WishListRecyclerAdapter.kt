package com.lemon.team.electronics.ui.wishList

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.search.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class WishListRecyclerAdapter(items: List<Content>, listener: WishListInteractionListener)
    : BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int = R.layout.item_wish_product

}