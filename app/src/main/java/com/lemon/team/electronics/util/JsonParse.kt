package com.lemon.team.electronics.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lemon.team.electronics.model.response.about.Companies

class JsonParse {

    fun getJsonParser(context: Context) {
        val jsonFileString = context.readJsonAsset( "companies.json")
        val gson = Gson()
        val listPersonType = object : TypeToken<Companies>() {}.type
        COMPANIES_LOGOS = gson.fromJson(jsonFileString, listPersonType)
    }


    companion object CompaniesLogo{
        lateinit var COMPANIES_LOGOS: Companies
    }

}

