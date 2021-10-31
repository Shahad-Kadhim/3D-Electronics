package com.lemon.team.electronics.ui.home

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

class HomeNestedAdapter(
    private val items: List<HomeItems<Any>>,
    private var listener: HomeInteractionListener
) : BaseRecyclerAdapter<Any>(items, listener) {

    override var layoutId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = setLayoutId(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun setLayoutId(viewType: Int): Int =
        when (viewType) {
            VIEW_TYPE_TOP_NAV -> R.layout.item_top_nav
            VIEW_TYPE_SLIDE_SHOW -> R.layout.item_slide_show
            VIEW_TYPE_SEARCH -> R.layout.item_search
            VIEW_TYPE_TITLE -> R.layout.item_title
            VIEW_TYPE_CATEGORIES -> R.layout.items_horizontal_host
            VIEW_TYPE_BEST_SELLER -> R.layout.items_vertical_host
            VIEW_TYPE_ELEMENTS_CATEGORIES -> R.layout.items_horizontal_host
            else -> R.layout.items_horizontal_host
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            bind(holder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        when (items[position].type) {
            HomeItemsType.TYPE_TOP_NAV ->
                holder.binding.setVariableItems(items[position].item, listener)
            HomeItemsType.TYPE_SLIDE_SHOW ->
                holder.binding.setVariableItems(items[position].item, listener)
            HomeItemsType.TYPE_SEARCH ->
                holder.binding.setVariableItems(items[position].item, listener)
            HomeItemsType.TYPE_TITLE ->
                holder.binding.setVariableItems(items[position].item, listener)
            HomeItemsType.TYPE_CATEGORIES ->
                holder.binding.setVariableAdapter(
                    CategoriesAdapter( items[position].item
                            as List<com.lemon.team.electronics.model.response.productsByCategoryId.Content>, listener))
            HomeItemsType.TYPE_BEST_SELLER ->
                holder.binding.setVariableAdapter(
                    BestSellerAdapter( items[position].item as List<ProductResponse>, listener))
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES ->
                holder.binding.setVariableAdapter(
                    ElementsCategoriesAdapter( items[position].item as List<CategoriesResponseItem>, listener))
        }
    }


    override fun getItemViewType(position: Int): Int =
        when (items[position].type) {
            HomeItemsType.TYPE_TOP_NAV -> VIEW_TYPE_TOP_NAV
            HomeItemsType.TYPE_SLIDE_SHOW -> VIEW_TYPE_SLIDE_SHOW
            HomeItemsType.TYPE_SEARCH -> VIEW_TYPE_SEARCH
            HomeItemsType.TYPE_TITLE -> VIEW_TYPE_TITLE
            HomeItemsType.TYPE_CATEGORIES -> VIEW_TYPE_CATEGORIES
            HomeItemsType.TYPE_BEST_SELLER -> VIEW_TYPE_BEST_SELLER
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES -> VIEW_TYPE_ELEMENTS_CATEGORIES
        }


    companion object {
        private const val VIEW_TYPE_TOP_NAV = 1
        private const val VIEW_TYPE_SLIDE_SHOW = 2
        private const val VIEW_TYPE_SEARCH = 3
        private const val VIEW_TYPE_TITLE = 4
        private const val VIEW_TYPE_CATEGORIES = 5
        private const val VIEW_TYPE_BEST_SELLER = 6
        private const val VIEW_TYPE_ELEMENTS_CATEGORIES = 7
    }

}


fun ViewDataBinding.setVariableAdapter(item: Any?) {
    this.setVariable(BR.adapter, item)
}


fun ViewDataBinding.setVariableItems(items: Any?, listener: BaseInteractionListener?) {
    this.setVariable(BR.item, items)
    this.setVariable(BR.listener, listener)
}
