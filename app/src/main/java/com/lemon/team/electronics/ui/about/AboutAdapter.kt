package com.lemon.team.electronics.ui.about

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.about.Companies
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter


class AboutAdapter(items: List<Companies>,
                        listener: BaseInteractionListener
): BaseRecyclerAdapter<Companies>(items, listener) {
    override val layoutId: Int = R.layout.item_horizontal_companies
}