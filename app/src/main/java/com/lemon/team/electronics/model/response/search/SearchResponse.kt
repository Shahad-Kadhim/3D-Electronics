package com.lemon.team.electronics.model.response.search


import com.google.gson.annotations.SerializedName
import com.lemon.team.electronics.model.response.Content

data class SearchResponse(
    @SerializedName("content")
    val content: List<Content>?,
    @SerializedName("empty")
    val empty: Boolean?,
    @SerializedName("first")
    val first: Boolean?,
    @SerializedName("last")
    val last: Boolean?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("numberOfElements")
    val numberOfElements: Int?,
    @SerializedName("pageable")
    val pageable: Pageable?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("sort")
    val sort: SortX?,
    @SerializedName("totalElements")
    val totalElements: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?
)