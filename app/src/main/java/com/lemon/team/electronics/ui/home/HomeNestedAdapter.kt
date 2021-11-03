package com.lemon.team.electronics.ui.home

import android.view.ViewGroup
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.ui.CategoriesAdapter
import com.lemon.team.electronics.ui.CategoryAdapter
import com.lemon.team.electronics.ui.CategoryInteractionListener
import com.lemon.team.electronics.ui.ProductInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.setVariableAdapter


class HomeNestedAdapter(
    private var itemsNested: MutableList<HomeItem>,
    private val listener: HomeInteractionListener,
) : BaseRecyclerAdapter<Any>(itemsNested, listener) {

    override var layoutId: Int = 0

    fun addItem(newItems: HomeItem) {
        itemsNested.apply {
            add(newItems)
            sortBy {
                it.rank
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayout(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayout(viewType: Int): Int =
        when (viewType) {
            TYPE_SLIDE_SHOW -> R.layout.items_slide_show_host
            TYPE_SEARCH -> R.layout.item_search
            TYPE_CATEGORIES -> R.layout.item_categories_recycle
            TYPE_BEST_SELLER -> R.layout.items_horizontal_host
            else -> R.layout.items_horizontal_host
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        itemsNested[position].apply {
            when (this) {
                is HomeItem.BestProductType -> {
                    holder.setVariableAdapter(BestSellerAdapter(items, listener))
                    holder.binding.setVariable(BR.title, title)
                }
                is HomeItem.CategoriesType -> {
                    holder.setVariableAdapter(CategoriesAdapter(items, listener as CategoryInteractionListener))
                }
                is HomeItem.ElementCategoriesType -> {
                    holder.setVariableAdapter(CategoryAdapter(items, listener as ProductInteractionListener))
                    holder.binding.setVariable(BR.title, title)
                }
                is HomeItem.SearchType -> {
                    holder.binding.setVariable(BR.listener,listener)
                }
                is HomeItem.SlideType -> {
                    holder.setVariableAdapter(SlideShowAdapter(items, listener as ProductInteractionListener))
                }

            }
        }
    }


    override fun getItemViewType(position: Int): Int =
        when (itemsNested[position]) {
            is HomeItem.SlideType -> TYPE_SLIDE_SHOW
            is HomeItem.SearchType -> TYPE_SEARCH
            is HomeItem.CategoriesType -> TYPE_CATEGORIES
            is HomeItem.BestProductType -> TYPE_BEST_SELLER
            is HomeItem.ElementCategoriesType -> TYPE_ELEMENT_CATEGORIES
        }

    companion object {
        const val TYPE_SLIDE_SHOW = 1
        const val TYPE_SEARCH = 2
        const val TYPE_CATEGORIES = 3
        const val TYPE_BEST_SELLER = 4
        const val TYPE_ELEMENT_CATEGORIES = 5
    }

}





