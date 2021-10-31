package com.lemon.team.electronics.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.asLiveData
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.response.categories.CategoriesResponse
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeNestedAdapter(
    private var itemsHome: List<HomeItems<Any>>,
    private val listener: HomeInteractionListener,
    private val lifecycleScope: LifecycleCoroutineScope
) : BaseRecyclerAdapter<Any>(itemsHome, listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setItemsNested(newHome: List<HomeItems<Any>>) {
        itemsHome = newHome
        this.notifyDataSetChanged()
    }

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
            else -> 0
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder && itemsHome.isNotEmpty())
             bind(holder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        when (itemsHome[position].type) {
            HomeItemsType.TYPE_SLIDE_SHOW -> holder.binding.setVariableAdapter(
                itemsHome[position].title, SlideShowAdapter(listOf("1", "2", "3"), listener))
            HomeItemsType.TYPE_SEARCH -> holder.binding.setVariableItems(listener)
            HomeItemsType.TYPE_CATEGORIES -> {
              lifecycleScope.launch {
                    Repository.getCategories().collect {
                        Log.i("sssssssssssCollect" , it.toString())
                         val list = it.toData() ?: listOf()
                        holder.binding.setVariableAdapter(itemsHome[position].title,
                            CategoriesAdapter(list , listener))
                    }
                }
            }
            HomeItemsType.TYPE_BEST_SELLER ->
                holder.binding.setVariableAdapter(
                    itemsHome[position].title,
                    BestSellerAdapter(itemsHome[position].items as List<ProductResponse>, listener)   )
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES ->
                holder.binding.setVariableAdapter(
                    itemsHome[position].title,
                    ElementsCategoriesAdapter(itemsHome[position].items as List<Content>, listener)  )
        }
    }


    override fun getItemViewType(position: Int): Int =
        when (itemsHome[position].type) {
            HomeItemsType.TYPE_SLIDE_SHOW -> VIEW_TYPE_SLIDE_SHOW
            HomeItemsType.TYPE_SEARCH -> VIEW_TYPE_SEARCH
            HomeItemsType.TYPE_CATEGORIES  -> VIEW_TYPE_CATEGORIES
            HomeItemsType.TYPE_BEST_SELLER  -> VIEW_TYPE_BEST_SELLER
            HomeItemsType.TYPE_ELEMENTS_CATEGORIES -> VIEW_TYPE_ELEMENTS_CATEGORIES
            else -> 0
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
    this.setVariable(BR.title, title)
    this.setVariable(BR.adapter, item)
}


fun ViewDataBinding.setVariableItems(listener: HomeInteractionListener?) {
    this.setVariable(BR.listener, listener)
}



