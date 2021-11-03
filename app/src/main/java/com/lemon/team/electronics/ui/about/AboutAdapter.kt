package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.local.CompaniesImgUrl
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter


class AboutAdapter(items: List<CompaniesImgUrl>,
                   listener: BaseInteractionListener
): BaseRecyclerAdapter<CompaniesImgUrl>(items, listener) {
    override val layoutId: Int = R.layout.item_horizontal_companies
}