package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.R


enum class HomeItemsType(val index: Int, val layout: Int) {
    TYPE_SLIDE_SHOW(0, R.layout.items_slide_show_host),
    TYPE_SEARCH(1, R.layout.item_search),
    TYPE_CATEGORIES(2, R.layout.items_horizontal_host),
    TYPE_BEST_SELLER(3, R.layout.items_horizontal_host),
    TYPE_ELEMENTS_CATEGORIES(4, R.layout.items_horizontal_host);
}