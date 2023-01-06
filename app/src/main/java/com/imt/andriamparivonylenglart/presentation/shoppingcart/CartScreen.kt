package com.imt.andriamparivonylenglart.presentation.shoppingcart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.imt.andriamparivonylenglart.R
import com.imt.andriamparivonylenglart.presentation.common.Title
import com.imt.andriamparivonylenglart.room.AppDatabase
import com.imt.andriamparivonylenglart.room.CartItem

@Composable
fun CartScreen() {
    val cartItems = AppDatabase.getInstance(LocalContext.current).cartItemDao().getAll()

    Column() {
        Title(title = stringResource(R.string.library_title))
        CartList(cartItems = cartItems)
    }
}

@Composable
fun CartList(cartItems: List<CartItem>) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(cartItems) { cartItem ->
            ItemCard(cartItem = cartItem)
        }
    }
}

@Composable
fun ItemCard(cartItem: CartItem) {
    val price = cartItem.price

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row() {
            AsyncImage(
                model = cartItem.cover,
                contentDescription = null,
                modifier = Modifier.height(120.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = cartItem.title, fontSize = 20.sp)
                Text(text = "$price €")
            }
        }
    }
}
