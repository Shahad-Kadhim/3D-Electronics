package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class CategoryAdapter(items: List<Content>,
                        listener: CategoryInteractionListener
): BaseRecyclerAdapter<Content>(items, listener) {
    override val layoutId: Int = R.layout.item_category_product
}