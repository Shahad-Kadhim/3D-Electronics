package com.lemon.team.electronics.model.response.categories


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categoryImageLocation")
    val categoryImageLocation: String?,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("id")
    val id: String
)