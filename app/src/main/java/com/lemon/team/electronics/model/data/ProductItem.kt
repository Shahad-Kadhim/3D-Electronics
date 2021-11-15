package com.lemon.team.electronics.model.data

import androidx.room.*


@Entity(tableName = "PRODUCT_TABLE")
data class ProductItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val itemId: String?,
    val name: String?,
    val oldPrice: Double?,
    val sold: Boolean?,
    val mainImage: String?,
    val price: Double?,
    val sale: Boolean?,
    val productType: Int?,
    val pieces: Int?
)
