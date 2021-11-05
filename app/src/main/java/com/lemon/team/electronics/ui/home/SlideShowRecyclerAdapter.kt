package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SlideShowRecyclerAdapter (
    items: List<Product>,
    listener: HomeInteractionListener
) : BaseRecyclerAdapter<Product>(items, listener) {

    override val layoutId: Int = R.layout.item_slide_image

}