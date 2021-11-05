package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.ui.base.*
import com.lemon.team.electronics.ui.home.HomeInteractionListener
import com.lemon.team.electronics.util.Constants

class CategoriesRecyclerAdapter(
    items: List<CategoryResponse>,
    listener: CategoryInteractionListener
) : BaseRecyclerAdapter<CategoryResponse>(items, listener) {

    override val layoutId: Int = R.layout.item_category

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is ItemViewHolder && getItems().isNotEmpty() ) {
            holder.binding.setVariable(BR.color, Constants.colors[position%6])
        }
    }
}
