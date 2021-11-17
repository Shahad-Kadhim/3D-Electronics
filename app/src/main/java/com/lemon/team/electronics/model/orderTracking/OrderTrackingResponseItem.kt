package com.lemon.team.electronics.model.orderTracking


import com.google.gson.annotations.SerializedName

data class OrderTrackingResponseItem(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("orderState")
    val orderState: String?
)