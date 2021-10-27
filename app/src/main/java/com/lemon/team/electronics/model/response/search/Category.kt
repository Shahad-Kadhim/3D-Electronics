package com.lemon.team.electronics.model.response.search


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("categoryName")
    val categoryName: String?,
    @SerializedName("id")
    val id: String?
)