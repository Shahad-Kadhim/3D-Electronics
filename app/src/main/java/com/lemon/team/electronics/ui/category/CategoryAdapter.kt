package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.ui.category.ProductInteractionListener

class CategoryAdapter(
    items: List<Content>,
    listener: ProductInteractionListener
): BaseRecyclerAdapter<Content>(items, listener) {
    override val layoutId: Int = R.layout.item_product
}