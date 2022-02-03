package com.lemon.team.electronics.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WISH_TABLE")
data class WishItem(
    @PrimaryKey val id: String,
    val name: String,
    val oldPrice: Double,
    val sold: Boolean,
    val mainImage: String,
    val price: Double,
    val sale: Boolean,
)
