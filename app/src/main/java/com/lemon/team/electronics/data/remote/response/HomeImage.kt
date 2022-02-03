package com.lemon.team.electronics.data.remote.response


import com.google.gson.annotations.SerializedName

data class HomeImage(
    @SerializedName("productHomeScreenPicLocation")
    val productImage: String?,
    @SerializedName("productId")
    val productId: String
)