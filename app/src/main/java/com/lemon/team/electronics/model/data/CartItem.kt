package com.lemon.team.electronics.model.data

import androidx.room.*


@Entity(tableName = "CART_TABLE")
data class CartItem(
    @PrimaryKey val id: String,
    val name: String,
    val oldPrice: Double,
    val sold: Boolean,
    val mainImage: String,
    val price: Double,
    val sale: Boolean,
    val pieces: Int,
)
