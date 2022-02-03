package com.lemon.team.electronics.util

import com.lemon.team.electronics.data.local.Companies

interface LocalResources {
    fun getCompanies(fileName: String): Companies
}