package com.lemon.team.electronics.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser

object DataClassParser {

    fun parseToJson(order: Any): JsonElement {
        return JsonParser.parseString(Gson().toJson(order))
    }

}