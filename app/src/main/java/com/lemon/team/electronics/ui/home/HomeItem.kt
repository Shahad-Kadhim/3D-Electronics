package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.Content


sealed class HomeItem(val rank: Int) {
    class SlideType(val items: List<Content>) : HomeItem(0)
    class SearchType : HomeItem(1)
    class CategoriesType(val items: List<CategoriesResponseItem>) : HomeItem(2)
    class BestProductType(val items: List<Content>, val title: String) : HomeItem(3)
    class ElementCategoriesType(val items: List<Content>, val title: String) : HomeItem(4)
}