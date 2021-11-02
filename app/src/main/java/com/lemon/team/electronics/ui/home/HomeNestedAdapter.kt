package com.lemon.team.electronics.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter


class HomeNestedAdapter(
    private var itemsHome: List<HomeItems<Any?>>,
    private val listener: HomeInteractionListener
) : BaseRecyclerAdapter<Any>(itemsHome, listener) {

    override var layoutId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = HomeItemsType.values()[viewType].layout
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        itemsHome[position].items?.let { bind(holder as ItemViewHolder, position) }
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        itemsHome[position].apply {
            when (getItemViewType(position)) {
                HomeItemsType.TYPE_SLIDE_SHOW.index ->
                    holder.setVariableAdapter(title, SlideShowAdapter(items as List<String>, listener))
                HomeItemsType.TYPE_SEARCH.index -> { }
                HomeItemsType.TYPE_CATEGORIES.index ->
                    holder.setVariableAdapter(title, CategoriesAdapter(items as List<CategoriesResponseItem>, listener))
                HomeItemsType.TYPE_BEST_SELLER.index ->
                    holder.setVariableAdapter(title, BestSellerAdapter(items as List<ProductResponse>, listener))
                HomeItemsType.TYPE_ELEMENTS_CATEGORIES.index ->
                    holder.setVariableAdapter(title, ElementsCategoriesAdapter(itemsHome[position].items as List<Content>, listener))
            }
        }
    }


    override fun getItemViewType(position: Int): Int = itemsHome[position].type


}


fun BaseRecyclerAdapter.ItemViewHolder.setVariableAdapter(title: Any?, item: Any?) {
    this.binding.setVariable(BR.title, title)
    this.binding.setVariable(BR.adapter, item)
}



