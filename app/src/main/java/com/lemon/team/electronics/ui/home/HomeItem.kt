package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.Content
import com.lemon.team.electronics.ui.home.HomeNestedAdapter.Companion.TYPE_SLIDE_SHOW


sealed class HomeItem(val type: HomeItemsType) {
    class SlideType(val items: List<String>) : HomeItem(HomeItemsType.SLIDE_SHOW)
    class SearchType : HomeItem(HomeItemsType.SEARCH)
    class CategoriesType(val items: List<CategoriesResponseItem>, val title: String) :
        HomeItem(HomeItemsType.CATEGORIES)
    class BestProductType(val items: List<ProductResponse>, val title: String) :
        HomeItem(HomeItemsType.BEST_SELLER)
    class ElementCategoriesType(val items: List<Content>, val title: String) :
        HomeItem(HomeItemsType.ELEMENTS_CATEGORIES)
}