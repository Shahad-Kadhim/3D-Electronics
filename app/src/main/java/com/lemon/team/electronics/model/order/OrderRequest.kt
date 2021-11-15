package com.lemon.team.electronics.model.order


import com.google.gson.annotations.SerializedName

data class OrderRequest(
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
    @SerializedName("mobileNumber")
    val mobileNumber: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("orderedProducts")
    val orderedProducts: List<OrderedProduct>?
)