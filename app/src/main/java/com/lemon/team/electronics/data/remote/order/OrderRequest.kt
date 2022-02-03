package com.lemon.team.electronics.data.remote.order


import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("city")
    val governorate: String?,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("district")
    val regionName: String?,
    @SerializedName("district2")
    val nearestPoint: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("mobileNumber")
    val mobileNumber: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("orderedProducts")
    val orderedProducts: List<OrderedProduct>?
)