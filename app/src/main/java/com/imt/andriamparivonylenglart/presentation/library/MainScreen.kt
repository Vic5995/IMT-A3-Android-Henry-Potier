package com.imt.andriamparivonylenglart.presentation.library

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.imt.andriamparivonylenglart.R
import com.imt.andriamparivonylenglart.Screen
import com.imt.andriamparivonylenglart.domain.Book

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Blue,
                title = {
                    Text(
                        text = stringResource(id = R.string.title),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
            )},
        content = {
            Column() {
                Title(title = stringResource(R.string.library_title))
                Library(viewModel = LibraryViewModel(), navController)
            }
        },
    )
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
        state.value?.books?.let { ListOrientationSetup(books = it, navController) }
    }
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
fun ListOrientationSetup(books: List<Book>, navController: NavController) {
    val configuration = LocalConfiguration.current

    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        BookListLandscapeDisplay(books, navController)
    } else {
        BookListPortraitDisplay(books, navController)
    }
}

@Composable
fun BookListPortraitDisplay(books: List<Book>, navController : NavController) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(books) { book ->
            BookCardPortraitDisplay(book, navController)
        }
    }
}

@Composable
fun BookListLandscapeDisplay(books: List<Book>, navController : NavController) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp)
    ) {

        items(books) { book ->
            BookCardLandscapeDisplay(book, navController)
        }
    }
}

@Composable
fun BookCardPortraitDisplay(book: Book, navController : NavController) {
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

@Composable
fun BookCardLandscapeDisplay(book: Book, navController : NavController) {
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
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            AsyncImage(
                model = book.cover,
                contentDescription = null,
                modifier = Modifier.height(120.dp)
            )
            Text(text = book.title, fontSize = 20.sp)
            Text(text = "$price €")
        }
    }
}
