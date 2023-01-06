package com.imt.andriamparivonylenglart.presentation.detail


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imt.andriamparivonylenglart.domain.Book
import com.imt.andriamparivonylenglart.room.AppDatabase
import com.imt.andriamparivonylenglart.room.CartItem


@Composable
fun DetailScreen(viewModel: BookViewModel){

    val state = viewModel.state.observeAsState()
    val refreshCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = refreshCount) {
        viewModel.loadBook()
    }
        state.value?.book?.let { Info(book = it) }

}

@Composable
fun Info(book : Book)
{
    val context = LocalContext.current
    var qty by remember { mutableStateOf(TextFieldValue("1")) }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ){
            Text(text = book.title, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(300.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = book.price, fontSize = 30.sp)

                OutlinedTextField(
                    value = qty,
                    label = { Text(text = "Qté") },
                    onValueChange = { newQty ->
                        qty = newQty
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.30f)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)
            ) {
                Button(
                    onClick = {
                        val cartItem = CartItem(book.isbn, book.title, book.price, book.cover, qty.toString().toInt())
                        Log.d("ADD : ", cartItem.toString())
                        AppDatabase.getInstance(context).cartItemDao().addCartItem(cartItem)
                    },
                ){
                    Text(text = "Ajouter au panier")
                }
            }
            book.synopsis.forEach {
                Text(text = it)
            }
        }
    }
}