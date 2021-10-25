package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @SerializedName("id")
    val id: String?,
    @SerializedName("productImageLocation")
    val productImageLocation: String?

)