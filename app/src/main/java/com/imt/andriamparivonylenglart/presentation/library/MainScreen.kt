package com.imt.andriamparivonylenglart.presentation.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.imt.andriamparivonylenglart.R
import com.imt.andriamparivonylenglart.Screen
import com.imt.andriamparivonylenglart.domain.Book
import com.imt.andriamparivonylenglart.presentation.common.Title
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@Composable
fun MainScreen(navController: NavController){
    Column() {
        Row() {
            Title(title = stringResource(R.string.library_title))
            Button(onClick = { navController.navigate("cart_screen") }) {
                Text(text = "Panier")
            }
        }
        Library(viewModel = LibraryViewModel(), navController)
    }
}

@Composable
fun Library(viewModel: LibraryViewModel, navController: NavController) {

    val state = viewModel.state.observeAsState()
    val refreshCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = refreshCount) {
        viewModel.loadBooks()
    }
    if (state.value?.isLoading == true) {
        Text(text = "Loading")
    } else {
        //Text(text = "There are ${state.value?.books?.size} books in your library! " )
        state.value?.books?.let { BookList(books = it, navController) }
    }

//    Text(text = if (state.value?.isLoading == true) "Loading" else "There are ${state.value?.books?.size} books in your library! ")
}

@Composable
fun BookList(books: List<Book>, navController : NavController) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(books) { book ->
            BookCard(book, navController)
        }
    }
}

@Composable
fun BookCard(book: Book, navController : NavController) {
    val price = book.price

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navController.navigate(Screen.DetailScreen.withArgs(book.isbn))
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row() {
            //TODO: put a placeholder under the image (if there is an error or not enough network ?)
            AsyncImage(
                model = book.cover,
                contentDescription = null,
                modifier = Modifier.height(120.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = book.title, fontSize = 20.sp)
                Text(text = "$price €")
            }

        }
    }
}
