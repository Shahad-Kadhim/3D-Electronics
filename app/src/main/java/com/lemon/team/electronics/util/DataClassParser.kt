package com.lemon.team.electronics.util

import com.google.gson.Gson

object DataClassParser {

    fun parseToJson(order: Any): String{
        return Gson().toJson(order)
    }

}