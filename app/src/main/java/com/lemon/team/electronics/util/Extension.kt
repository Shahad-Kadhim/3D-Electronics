package com.lemon.team.electronics.util

import android.view.View
import android.widget.EditText
import androidx.navigation.*
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter

fun View.goToFragment(navDir: NavDirections) {
    Navigation.findNavController(this).navigate(navDir)
}

fun EditText.onClickSearch(action : (String) -> Unit) {
    this.setOnEditorActionListener { view, _, _ ->
        if (view.text.isNotBlank()) {
            action(view.text.toString())
        }
        return@setOnEditorActionListener false
    }
}

fun BaseRecyclerAdapter.ItemViewHolder.setVariableAdapter(item: Any?) {
    this.binding.setVariable(BR.adapter, item)
}