package com.lemon.team.electronics.util

import com.lemon.team.electronics.model.response.about.Companies

interface LocalInteractionListener {
    fun getJsonParser(fileName: String): Companies
}