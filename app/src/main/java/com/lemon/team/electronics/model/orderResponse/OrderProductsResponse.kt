package com.lemon.team.electronics.model.orderResponse


import com.google.gson.annotations.SerializedName

data class OrderProductsResponse(
    @SerializedName("productCount")
    val productCount: Int?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productPrice")
    val productPrice: Double?,
    @SerializedName("totalAmount")
    val totalAmount: Double?
)