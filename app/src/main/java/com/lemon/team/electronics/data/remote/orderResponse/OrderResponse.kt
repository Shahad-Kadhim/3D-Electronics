package com.lemon.team.electronics.data.remote.orderResponse


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("city")
    val city: String?,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("district2")
    val district2: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("extraInfoOrder")
    val extraInfoOrder: Any?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("itemAmount")
    val itemAmount: Int?,
    @SerializedName("localDate")
    val localDate: String?,
    @SerializedName("mobileNumber")
    val mobileNumber: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("orderProductsResponse")
    val orderProductsResponse: List<OrderProductsResponse>?,
    @SerializedName("orderState")
    val orderState: String?,
    @SerializedName("orderTrackId")
    val orderTrackId: String?,
    @SerializedName("totalAmount")
    val totalAmount: Double?
)