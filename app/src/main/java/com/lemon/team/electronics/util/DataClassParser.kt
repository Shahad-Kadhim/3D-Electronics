package com.lemon.team.electronics.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import javax.inject.Inject

class DataClassParser @Inject constructor(
    private val gson: Gson
) {

    fun parseToJson(order: Any): JsonElement {
        return JsonParser.parseString(gson.toJson(order))
    }

}