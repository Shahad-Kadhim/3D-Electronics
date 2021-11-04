package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("content")
    val products: List<Product>?,
    @SerializedName("empty")
    val isEmpty: Boolean?,
    @SerializedName("first")
    val isFirst: Boolean?,
    @SerializedName("last")
    val isLast: Boolean?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("numberOfElements")
    val numberOfElements: Int?,
    @SerializedName("pageable")
    val pageable: Pageable?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("sort")
    val sort: Sort?,
    @SerializedName("totalElements")
    val totalElements: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?
)