package com.imt.andriamparivonylenglart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.imt.andriamparivonylenglart.ui.theme.AndriamparivonyLenglartTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
            /*AndriamparivonyLenglartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Title(title = stringResource(R.string.library_title))
                        Library(viewModel = viewModel)
                    }
                }
            }*/
        }
    }
}

@Composable
fun Library(viewModel: LibraryViewModel) {

    val state = viewModel.state.observeAsState()
    val refreshCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = refreshCount) {
        viewModel.loadBooks()
    }
    if (state.value?.isLoading == true) {
        Text(text = "Loading")
    } else {
        //Text(text = "There are ${state.value?.books?.size} books in your library! " )
        state.value?.books?.let { BookList(books = it) }
    }

//    Text(text = if (state.value?.isLoading == true) "Loading" else "There are ${state.value?.books?.size} books in your library! ")
}

@Composable
fun Title(title: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 30.sp)
    }
}

@Composable
fun BookList(books: List<Book>) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(books) { book ->
            BookCard(coverUrl = book.cover, title = book.title, price = book.price)
        }
    }
}

@Composable
fun BookCard(coverUrl: String, title: String, price: String) {
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
            //TODO: put a placeholder under the image (if there is an error or not enough network ?)
            AsyncImage(
                model = coverUrl,
                contentDescription = null,
                modifier = Modifier.height(120.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = title, fontSize = 20.sp)
                Text(text = "$price €")
            }

        }
    }
}