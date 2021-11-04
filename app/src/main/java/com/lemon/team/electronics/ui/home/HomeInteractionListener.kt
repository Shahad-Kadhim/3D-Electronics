package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.Content
import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productsByCategoryId.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)
    fun onClickHeart(productId: String)
    fun onClickCategory(categoryId: CategoriesResponseItem)
    fun onclickSearch()
    fun onClickSeeMoreForBestSeller()
    fun onClickSeeMoreForCategories()
    fun onClickSeeMoreForCategory(category: CategoryInfoType)

}