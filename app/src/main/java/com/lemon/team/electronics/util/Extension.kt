package com.lemon.team.electronics.util

import android.view.View
import androidx.navigation.*

fun View.goToFragment(navDir: NavDirections) {
    Navigation.findNavController(this).navigate(navDir)
}
