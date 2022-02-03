package com.lemon.team.electronics.data.remote.orderTracking


import com.google.gson.annotations.SerializedName

data class OrderTrackingResponse(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("orderState")
    val orderState: String?
)