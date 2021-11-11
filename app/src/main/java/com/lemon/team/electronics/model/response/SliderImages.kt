package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class SliderImages(
    @SerializedName("productHomeScreenPicLocation")
    val sliderImage: String?,
    @SerializedName("productId")
    val productId: String?
)