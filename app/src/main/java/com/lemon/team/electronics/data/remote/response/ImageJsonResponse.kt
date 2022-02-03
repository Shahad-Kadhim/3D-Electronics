package com.lemon.team.electronics.data.remote.response


import com.google.gson.annotations.SerializedName

data class ImageJsonResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("productImageLocation")
    val productImageLocation: String?
)