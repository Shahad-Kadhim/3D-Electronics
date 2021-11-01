package com.lemon.team.electronics.util

import android.content.Context

class JsonReader {

    fun getJsonString(context: Context)=
        context.readJsonAsset( "companies.json")

}