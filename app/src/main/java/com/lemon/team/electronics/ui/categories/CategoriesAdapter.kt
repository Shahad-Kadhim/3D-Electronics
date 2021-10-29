package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.ui.base.*

class CategoriesAdapter(items: List<CategoriesResponseItem>,
                        listener: CategoriesInteractionListener
): BaseRecyclerAdapter<CategoriesResponseItem>(items, listener) {
    override val layoutId: Int = R.layout.item_category
}
