package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.CategoryResponse
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.model.response.productsByCategoryId.CategoryInfoType


sealed class HomeItem(val rank: Int) {
    class SlideType(val items: List<Product>) : HomeItem(0)
    class SearchType : HomeItem(1)
    class CategoriesType(val items: List<CategoryResponse>) : HomeItem(2)
    class BestProductType(val items: List<Product>) : HomeItem(3)
    class ElementCategoriesType(
        val items: List<Product>, val categoryInfo: CategoryInfoType
    ) : HomeItem(4)
}