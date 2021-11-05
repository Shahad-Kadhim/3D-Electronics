package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.ColorsRecycler
import com.lemon.team.electronics.util.Constants

class CategoriesAdapter(
    items: List<CategoryResponse>,
    val listener: HomeInteractionListener
) : BaseRecyclerAdapter<CategoryResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_categories

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (holder is ItemViewHolder && getItems().isNotEmpty() ) {
            holder.binding.setVariable(BR.color, Constants.colors[position%6])
        }
    }
}
