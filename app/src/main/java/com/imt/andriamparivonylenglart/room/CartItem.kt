package com.imt.andriamparivonylenglart.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem (
    @PrimaryKey val isbn: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "quantity") val quantity: Int
    )