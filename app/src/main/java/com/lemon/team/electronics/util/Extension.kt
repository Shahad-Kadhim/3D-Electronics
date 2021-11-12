package com.lemon.team.electronics.util

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import com.lemon.team.electronics.BR
import com.lemon.team.electronics.R
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import java.io.IOException


fun View.goToFragment(navDir: NavDirections) {
    Navigation.findNavController(this).navigate(navDir)
}

fun View.goToFragmentWithTransition(navDir: NavDirections,extra: FragmentNavigator.Extras) {
    Navigation.findNavController(this).navigate(navDir,extra)
}

fun Context.readJsonAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
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

fun Intent.sharingUrl(url: String?): Intent? {
    return  this.apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "product link")
        putExtra(Intent.EXTRA_TEXT, Constants.URL_PRODUCT_WEBSITE +url)
    }
}


fun <T> getSixItems(itemList: List<T>) = itemList.take(6)


fun String.convertToCategoryIcon(): Int =
    when(this){
        CategoriesId.MIC -> R.drawable.ic_mic
        CategoriesId.ADAPTER -> R.drawable.ic_cable
        CategoriesId.CASE -> R.drawable.ic_case_icon
        CategoriesId.TABLET -> R.drawable.ic_ci_tablet
        CategoriesId.CPU -> R.drawable.ic_cpu
        CategoriesId.GRAPHICS_CARD -> R.drawable.ic_graphics_card
        CategoriesId.LAPTOP_FAN,
        CategoriesId.CASE_FAN-> R.drawable.ic_fan
        CategoriesId.SIMULATORS -> R.drawable.ic_game
        CategoriesId.HEADSETS ,
        CategoriesId.REPLACEMENT_EAR_PADS-> R.drawable.ic_headphone
        CategoriesId.STORAGE_AND_MEMORY,
        CategoriesId.EXTERNAL_HARD-> R.drawable.ic_storage
        CategoriesId.GAMING_CHAIRS-> R.drawable.ic_chairs
        CategoriesId.LAPTOP -> R.drawable.ic_laptop
        CategoriesId.WEBCAM -> R.drawable.ic_webcam
        CategoriesId.MONITOR_STAND -> R.drawable.ic_stand
        CategoriesId.POWER_PRODUCTS ,
        CategoriesId.POWER_SUPPLY -> R.drawable.ic_power_supply
        CategoriesId.FLASH_DRIVES -> R.drawable.ic_memory
        CategoriesId.MOUSE -> R.drawable.ic_mouse
        CategoriesId.KEYBOARD -> R.drawable.ic_keyboard
        CategoriesId.MOTHERBOARD -> R.drawable.ic_motherboard
        CategoriesId.PAD_MOUSE -> R.drawable.ic_mousepad
        CategoriesId.TABLET_AND_MOBILES -> R.drawable.ic_mobile
        CategoriesId.MONITORS -> R.drawable.ic_monitor
        else -> R.drawable.ic_pc
}
