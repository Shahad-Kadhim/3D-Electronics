package com.lemon.team.electronics.ui.home

import android.util.Log
import android.view.ViewGroup
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.setVariableAdapter


class HomeNestedAdapter(
    private var itemsNested: MutableList<HomeItem>,
    private val listener: HomeInteractionListener
) : BaseRecyclerAdapter<Any>(itemsNested, listener) {

    override var layoutId: Int = 0

    fun addItem(newItems: HomeItem) {
        itemsNested.addAll(listOf(newItems).sortedBy { it.type.index })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = HomeItemsType.values()[viewType].layout
        return super.onCreateViewHolder(parent, viewType)
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
                    holder.setVariableAdapter(CategoriesAdapter(items, listener))
                    holder.binding.setVariable(BR.title, title)
                }
                is HomeItem.ElementCategoriesType -> {
                    holder.setVariableAdapter(ElementsCategoriesAdapter(items, listener))
                    holder.binding.setVariable(BR.title, title)
                }
                is HomeItem.SearchType -> {  }
                is HomeItem.SlideType -> {
                    holder.setVariableAdapter(SlideShowAdapter(items, listener))
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int = itemsNested[position].type.index

}





