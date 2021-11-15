package com.lemon.team.electronics.model.response

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("category")
    val category: Category?,
    @SerializedName("comingSoon")
    val comingSoon: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("homeScreenPicLocation")
    val homeScreenPicLocation: Any?,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageJsonResponses")
    val images: List<ImageJsonResponse>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("oldPrice")
    val oldPrice: Double?,
    @SerializedName("onlyShopAvailable")
    val onlyShopAvailable: Boolean?,
    @SerializedName("outOfStock")
    val sold: Boolean?,
    @SerializedName("picLocation")
    val mainImage: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("priceAssemble")
    val priceAssemble: Double?,
    @SerializedName("recommended")
    val recommended: Boolean?,
    @SerializedName("sale")
    val sale: Boolean?
)