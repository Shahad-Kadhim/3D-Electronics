package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("empty")
    val isEmpty: Boolean?,
    @SerializedName("sorted")
    val isSorted: Boolean?,
    @SerializedName("unsorted")
    val isUnsorted: Boolean?
)