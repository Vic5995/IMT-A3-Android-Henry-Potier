package com.imt.andriamparivonylenglart.room

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CartItemRepo(private val cartItemDao: CartItemDao) {
    val allCartItem = MutableLiveData<List<CartItem>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addCartItem(newCartItem: CartItem) {
        coroutineScope.launch(Dispatchers.IO) {
            cartItemDao.addCartItem(newCartItem)
        }
    }
}