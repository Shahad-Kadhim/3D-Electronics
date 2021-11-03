package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.local.CompaniesImgUrl
import com.lemon.team.electronics.ui.base.*

class CompaniesAdapter(
    items: List<CompaniesImgUrl>,
    listener: BaseInteractionListener
): BaseRecyclerAdapter<CompaniesImgUrl>(items, listener) {

    override val layoutId: Int = R.layout.item_companies_recycler

}