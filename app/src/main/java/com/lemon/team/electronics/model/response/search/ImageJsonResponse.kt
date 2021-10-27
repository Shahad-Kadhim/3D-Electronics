package com.lemon.team.electronics.model.response.search


import com.google.gson.annotations.SerializedName

data class ImageJsonResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("productImageLocation")
    val productImageLocation: String?
)