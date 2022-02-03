package com.lemon.team.electronics.util

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lemon.team.electronics.data.local.Companies
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class LocalData @Inject constructor(
    private val gson: Gson,
    private val jsonReader: JsonReader
): LocalResources {

    fun setContext(applicationContext: Context) {
        context = applicationContext
    }

    private fun getJsonString(fileName: String) =
        jsonReader.getJsonString(context, fileName)

    override fun getCompanies(fileName: String): Companies =
        ((gson.fromJson(getJsonString(fileName),object : TypeToken<Companies>() {}.type)))



    companion object {
        private lateinit var context: Context
    }

}

