package com.lemon.team.electronics.util

import android.view.View
import androidx.navigation.*
import com.lemon.team.electronics.model.response.categories.*

fun View.goToFragment(navDir: NavDirections) {
    Navigation.findNavController(this).navigate(navDir)
}


fun CategoriesResponse.convertToLocalCategoriesResponse(): LocalCategoriesResponse{
    return LocalCategoriesResponse(this.map{ it })
}