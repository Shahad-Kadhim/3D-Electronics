package com.lemon.team.electronics.data.remote.order


import com.google.gson.annotations.SerializedName

data class OrderedProduct(
    @SerializedName("productCount")
    val productCount: Int?,
    @SerializedName("productId")
    val productId: String?
)