package com.lemon.team.electronics.ui.home

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeNestedAdapter(
    private val items: List<HomeItems<Any>>,
    private var listener: HomeInteractionListener
) : BaseRecyclerAdapter<Any>(items, listener) {

    override var layoutId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayoutId(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayoutId(viewType: Int): Int =
        when (viewType) {
            VIEW_TYPE_SLIDE_SHOW -> R.layout.items_slide_show_host
            VIEW_TYPE_SEARCH -> R.layout.item_search
            VIEW_TYPE_CATEGORIES, VIEW_TYPE_BEST_SELLER,
            VIEW_TYPE_ELEMENTS_CATEGORIES -> R.layout.items_horizontal_host
            else -> R.layout.items_slide_show_host
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            bind(holder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        when (items[position].type) {
            HomeItemsType.TYPE_SLIDE_SHOW -> holder.binding.setVariableAdapter(items[position].item,
                SlideShowAdapter(listOf("1", "2", "3"), listener))
            HomeItemsType.TYPE_SEARCH -> holder.binding.setVariableItems(items[position].item, listener)
            HomeItemsType.TYPE_CATEGORIES -> {
                Log.i("sssssssssssMap" , getData(Repository.getCategories()).toString())
            }
            HomeItemsType.TYPE_BEST_SELLER ->
                holder.binding.setVariableAdapter(items[position].item, BestSellerAdapter(emptyList(), listener))
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES ->
                holder.binding.setVariableAdapter(items[position].item,
                    ElementsCategoriesAdapter(emptyList(), listener))
        }
    }

    private fun <T> getData(value: Flow<State<T?>>): Any {
        Log.i("sssssssssssData" , value.toString())
        return checkState(value.map{ it.toData() })
    }

    private fun <T> checkState(state: T?): Any {
        Log.i("sssssssssssState" , state.toString())
        return (state ?: emptyList<T>())
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position].type) {
            HomeItemsType.TYPE_SLIDE_SHOW -> VIEW_TYPE_SLIDE_SHOW
            HomeItemsType.TYPE_SEARCH -> VIEW_TYPE_SEARCH
            HomeItemsType.TYPE_CATEGORIES -> VIEW_TYPE_CATEGORIES
            HomeItemsType.TYPE_BEST_SELLER -> VIEW_TYPE_BEST_SELLER
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES -> VIEW_TYPE_ELEMENTS_CATEGORIES
        }


    companion object {
        private const val VIEW_TYPE_SLIDE_SHOW = 1
        private const val VIEW_TYPE_SEARCH = 2
        private const val VIEW_TYPE_CATEGORIES = 3
        private const val VIEW_TYPE_BEST_SELLER = 4
        private const val VIEW_TYPE_ELEMENTS_CATEGORIES = 5
    }

}


fun ViewDataBinding.setVariableAdapter(title: Any?, item: Any?) {
    this.setVariable(BR.adapter, item)
    this.setVariable(BR.title, title)
}


fun ViewDataBinding.setVariableItems(items: Any?, listener: BaseInteractionListener?) {
    this.setVariable(BR.listener, listener)
}



