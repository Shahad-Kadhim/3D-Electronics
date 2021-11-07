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

fun String.convertToCategoryIcon(): Int =
    when(this){
        "239b5fa5-663c-4a18-94ad-1f25f980f844" -> R.drawable.ic_mic
        "0565cf6c-f709-43f7-a421-1628ee31450b" -> R.drawable.ic_cable
        "93102032-c8f3-43c0-b375-d49c2a2dc51e" -> R.drawable.ic_case_icon
        "109d1b73-8bd4-4208-bb03-86e5c06ee43f" -> R.drawable.ic_ci_tablet
        "6f9a6cf4-569f-4c22-b60e-d8c7ae626e19" -> R.drawable.ic_cpu
        "eea18111-2d34-4341-88f5-df4f8845667d" -> R.drawable.ic_graphics_card
        "fb9ced44-5e64-4203-9472-532001e7f611",
        "4eaab7c4-6ef4-4b76-bc52-6c5f07cd9c95"-> R.drawable.ic_fan
        "6d551d73-c2f2-4d0f-ae1e-f23b92500d44" -> R.drawable.ic_game
        "f9d895e5-b65c-4393-92fd-52a5a4d65f3a" ,
        "57289db8-1692-40f3-9c2e-beb49b9be0bc"-> R.drawable.ic_headphone
        "64cf573a-822e-4a82-90e9-67378b989b94",
        "2abbd3d7-bc41-429d-9e63-9a6591d02300"-> R.drawable.ic_storage
        "02fd28ec-0ed2-4998-ac22-3b06debe1977"-> R.drawable.ic_chairs
        "d45cabca-4034-4d7f-9a9a-ad2f06f92f34" -> R.drawable.ic_laptop
        "3a909794-763b-485e-a3c5-9e9854d383fe" -> R.drawable.ic_webcam
        "d94891af-77d6-4aa0-8b00-b84e0d10be22" -> R.drawable.ic_stand
        "68290e39-2cf6-4f25-82c9-2bacd2e80758" ,
        "f2d1445c-2214-4f5f-8522-7bafefb3c47d" -> R.drawable.ic_power_supply
        "81ddceae-3dfd-4949-9ef0-59b6c2d6ad46" -> R.drawable.ic_memory
        "3c5f8fb8-0fff-44af-9b2d-df3b2d423599" -> R.drawable.ic_mouse
        "4b1e4d14-6d2c-485d-b41e-eef6183e1f49" -> R.drawable.ic_keyboard
        "201399b8-2943-435f-9999-0380891fb208" -> R.drawable.ic_motherboard
        "56b272ce-bbba-421c-bda0-0166b1f4e48b" -> R.drawable.ic_mousepad
        "5efc0216-da50-47fc-97a5-55a5083cdccf" -> R.drawable.ic_mobile
        "1a4d5d99-b0c8-4f95-9e1e-1e2f0ab93863" -> R.drawable.ic_monitor
        else -> R.drawable.ic_pc
}
