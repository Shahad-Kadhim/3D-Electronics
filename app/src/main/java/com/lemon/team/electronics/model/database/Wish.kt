package com.lemon.team.electronics.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "WISH_TABLE")
data class Wish(
    @PrimaryKey(autoGenerate = true)val id: Long,
    val itemId: String,
    val name: String?,
    val oldPrice: Double?,
    val sold: Boolean?,
    val mainImage: String?,
    val price: Double?,
    val sale: Boolean?
)
