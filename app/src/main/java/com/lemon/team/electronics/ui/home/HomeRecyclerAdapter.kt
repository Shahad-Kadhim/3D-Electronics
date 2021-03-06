package com.lemon.team.electronics.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.data.remote.response.CategoryResponse
import com.lemon.team.electronics.data.remote.response.Product
import com.lemon.team.electronics.ui.base.AppDiffUtil
import com.lemon.team.electronics.ui.base.BaseInteractionListener
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.util.getSixItems
import com.lemon.team.electronics.util.setVariableAdapter


class HomeRecyclerAdapter(
    private var itemsNested: MutableList<HomeItem>,
    private val listener: HomeInteractionListener
) : BaseRecyclerAdapter<HomeItem>(itemsNested, listener) {

    override var layoutId: Int = 0

    fun addItem(newItems: HomeItem) {
        val newItemsList = itemsNested.apply {
            add(newItems)
            sortBy { item ->
                item.rank
            }
        }
        val diffResult = DiffUtil.calculateDiff(AppDiffUtil(itemsNested,
            newItemsList,
            ::areItemsTheSame,
            ::areContentSame))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ) =
        getItems()[oldItemPosition].rank == (newItems[newItemPosition] as HomeItem).rank

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayout(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayout(viewType: Int): Int =
        when (viewType) {
            TYPE_SLIDE_SHOW -> R.layout.item_slider
            TYPE_SEARCH -> R.layout.item_search
            TYPE_CATEGORIES -> R.layout.item_categories_recycle
            TYPE_MOST_WANTED_PRODUCTS -> R.layout.items_horizontal_best_seller_host
            else -> R.layout.items_horizontal_category_host
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        when (val currentItem = itemsNested[position]) {
            is HomeItem.BestSellerType -> {
                holder.setVariableAdapter(BestSellerRecycler(currentItem.items, listener))
            }
            is HomeItem.CategoriesType -> {
                holder.setVariableAdapter(CategoriesAdapter((currentItem.items).getSixItems(),
                    listener))
            }
            is HomeItem.ElementCategoriesType -> {
                holder.setVariableAdapter(CategoryAdapter(currentItem.items, listener))
                holder.binding.setVariable(BR.categoryInfo, currentItem.categoryInfo)
            }
            is HomeItem.SearchType -> {
                holder.binding.setVariable(BR.listener, listener)
            }
            is HomeItem.SlideType -> {
                holder.binding.setVariable(BR.products, currentItem.items)
                holder.binding.setVariable(BR.listener, listener)
            }
        }
    }


    override fun getItemViewType(position: Int): Int =
        when (itemsNested[position]) {
            is HomeItem.SlideType -> TYPE_SLIDE_SHOW
            is HomeItem.SearchType -> TYPE_SEARCH
            is HomeItem.CategoriesType -> TYPE_CATEGORIES
            is HomeItem.BestSellerType -> TYPE_MOST_WANTED_PRODUCTS
            is HomeItem.ElementCategoriesType -> TYPE_ELEMENT_CATEGORIES
        }

    companion object {
        const val TYPE_SLIDE_SHOW = 1
        const val TYPE_SEARCH = 2
        const val TYPE_CATEGORIES = 3
        const val TYPE_MOST_WANTED_PRODUCTS = 4
        const val TYPE_ELEMENT_CATEGORIES = 5
    }

}

interface HomeInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)
    fun onClickShare(productId: String)
    fun onClickCategory(categoryId: CategoryResponse)
    fun onclickAddToCart(productId: Product)
    fun onclickSearch()
    fun onClickSeeMoreForCategories()
    fun onClickSeeMoreForCategory(category: CategoryInfoType)
    fun onClickSliderItem(position: Int)

}




