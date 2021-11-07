package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.domain.CategoryInfoType
import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {

    fun onClickProduct(productId: String)
    fun onClickHeart(productId: String)
    fun onClickShare(productId: String)
    fun onClickCategory(categoryId: CategoryResponse)
    fun onclickSearch()
    fun onClickSeeMoreForBestSeller()
    fun onClickSeeMoreForCategories()
    fun onClickSeeMoreForCategory(category: CategoryInfoType)

}