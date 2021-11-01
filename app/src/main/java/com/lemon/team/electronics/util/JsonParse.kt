package com.lemon.team.electronics.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lemon.team.electronics.model.response.about.Companies

class JsonParse {

    fun getJsonParser(context: Context): Companies {
        COMPANIES_LOGOS = (Gson().fromJson(JsonReader().getJsonString(context), getTypeOfJson()))
        return COMPANIES_LOGOS
    }

    fun getJsonParser() = COMPANIES_LOGOS


    private fun getTypeOfJson() =
        object : TypeToken<Companies>() {}.type



    companion object CompaniesLogo{
        private lateinit var COMPANIES_LOGOS: Companies
    }

}

