package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class HomeImagesItem(
    @SerializedName("productHomeScreenPicLocation")
    val productImage: String? = null,
    @SerializedName("productId")
    val productId: String
)