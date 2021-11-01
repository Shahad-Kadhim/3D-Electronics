package com.lemon.team.electronics.ui.cart

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CartRecyclerAdapter(items: List<Content>, listener: CartInteractionListener)
    : BaseRecyclerAdapter<Content>(items, listener) {

    override val layoutId: Int = R.layout.item_product_in_cart

}