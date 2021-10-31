package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class SlideShowAdapter (
    items: List<String>,
    listener: HomeInteractionListener) : BaseRecyclerAdapter<String>(items, listener) {

    override val layoutId: Int = R.layout.item_slide_show

}