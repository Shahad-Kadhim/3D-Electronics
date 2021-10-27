package com.lemon.team.electronics.model.response.productsByCategoryId


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("empty")
    val empty: Boolean?,
    @SerializedName("sorted")
    val sorted: Boolean?,
    @SerializedName("unsorted")
    val unsorted: Boolean?
)