package com.lemon.team.electronics.model.response.about

import com.google.gson.annotations.SerializedName

data class Companies(
    @SerializedName("CompaniesImgUrl")
    val companiesImgUrl: List<CompaniesImgUrl>? = null,
    @SerializedName("OtherCompaniesImgUrl")
    val otherCompaniesImgUrl: List<CompaniesImgUrl>? = null
)