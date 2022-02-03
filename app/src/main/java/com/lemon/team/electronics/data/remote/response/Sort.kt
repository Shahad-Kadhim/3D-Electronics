package com.lemon.team.electronics.data.remote.response


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("empty")
    val isEmpty: Boolean?,
    @SerializedName("sorted")
    val isSorted: Boolean?,
    @SerializedName("unsorted")
    val isUnsorted: Boolean?
)