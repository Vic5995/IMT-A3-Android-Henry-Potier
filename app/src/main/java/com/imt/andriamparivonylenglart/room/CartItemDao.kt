package com.imt.andriamparivonylenglart.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cartitem")
    fun getAll(): List<CartItem>

    @Query("SELECT * FROM cartitem WHERE isbn = :isbn")
    fun getByISBN(isbn: String): CartItem

    @Update
    fun updateCartItem(updatedCartItem: CartItem)

    @Delete
    fun deleteCartItem(deletedCartItem: CartItem)

    @Insert
    fun addCartItem(addedCartItem: CartItem)
}