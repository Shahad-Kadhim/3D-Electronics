package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class Pageable(

    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("pageNumber")
    val pageNumber: Int?,
    @SerializedName("pageSize")
    val pageSize: Int?,
    @SerializedName("paged")
    val paged: Boolean?,
    @SerializedName("sort")
    val sort: Sort?,
    @SerializedName("unpaged")
    val unpaged: Boolean?

)