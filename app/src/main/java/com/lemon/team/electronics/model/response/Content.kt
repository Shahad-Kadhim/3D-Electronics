package com.lemon.team.electronics.model.response


import com.google.gson.annotations.SerializedName
import com.lemon.team.electronics.model.response.search.Category
import com.lemon.team.electronics.model.response.search.ImageJsonResponse

data class Content(
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
    val imageJsonResponses: List<ImageJsonResponse>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("oldPrice")
    val oldPrice: Double?,
    @SerializedName("onlyShopAvailable")
    val onlyShopAvailable: Boolean?,
    @SerializedName("outOfStock")
    val outOfStock: Boolean?,
    @SerializedName("picLocation")
    val picLocation: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("priceAssemble")
    val priceAssemble: Double?,
    @SerializedName("recommended")
    val recommended: Boolean?,
    @SerializedName("sale")
    val sale: Boolean?
)