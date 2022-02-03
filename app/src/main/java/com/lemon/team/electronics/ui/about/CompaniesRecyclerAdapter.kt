package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.local.CompaniesImgUrl
import com.lemon.team.electronics.ui.base.*

class CompaniesRecyclerAdapter(
    items: List<CompaniesImgUrl>,
    listener: BaseInteractionListener
): BaseRecyclerAdapter<CompaniesImgUrl>(items, listener) {

    override val layoutId: Int = R.layout.item_companies_recycler

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) = true

}