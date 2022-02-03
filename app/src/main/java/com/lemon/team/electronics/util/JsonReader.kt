package com.lemon.team.electronics.util

import android.content.Context
import javax.inject.Inject

class JsonReader @Inject constructor() {

    fun getJsonString(context: Context, jsonFileName: String)=
        context.readJsonAsset(jsonFileName)

}