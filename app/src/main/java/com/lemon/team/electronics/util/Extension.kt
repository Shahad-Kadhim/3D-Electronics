package com.lemon.team.electronics.util

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.*
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.model.response.productById.ProductResponse
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
fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, f:(T) ->Unit){
    this.observe(owner, EventObserver{
        f(it)
    })
}
