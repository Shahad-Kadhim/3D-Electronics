package com.lemon.team.electronics.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WISH_TABLE")
data class WishItem(
    @PrimaryKey val itemId: String,
    val name: String,
    val oldPrice: Double,
    val sold: Boolean,
    val mainImage: String,
    val price: Double,
    val sale: Boolean,
)
