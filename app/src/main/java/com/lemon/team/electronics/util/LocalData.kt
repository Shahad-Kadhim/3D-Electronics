package com.lemon.team.electronics.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lemon.team.electronics.data.local.Companies
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalData @Inject constructor(
    private val gson: Gson,
    private val jsonReader: JsonReader,
    @ApplicationContext private val context: Context
): LocalResources {


    private fun getJsonString(fileName: String) =
        jsonReader.getJsonString(context, fileName)

    override fun getCompanies(fileName: String): Companies =
        ((gson.fromJson(getJsonString(fileName),object : TypeToken<Companies>() {}.type)))


}

