package com.imt.andriamparivonylenglart.presentation.detail


import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.imt.andriamparivonylenglart.R
import com.imt.andriamparivonylenglart.domain.Book


@Composable
fun DetailScreen(viewModel: BookViewModel, navController: NavController){

    val state = viewModel.state.observeAsState()
    val refreshCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = refreshCount) {
        viewModel.loadBook()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Blue,
                title = {
                    Text(
                        text = state.value?.book?.title ?: stringResource(id = R.string.title),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Rounded.ArrowBack, "")
                    }
                }
            )},
        content = {
            state.value?.book?.let { OrientationSetup(book = it) }
        },
    )
}

@Composable
fun OrientationSetup(book : Book) {
    val configuration = LocalConfiguration.current
    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeBookDisplay(book = book)
    } else {
        PortraitBookDisplay(book)
    }
}

@Composable
fun LandscapeBookDisplay(book : Book)
{
    var qty by remember { mutableStateOf(TextFieldValue("1")) }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ){
            Column() {
                AsyncImage(
                    model = book.cover,
                    contentDescription = null,
                    modifier = Modifier
                        .height(350.dp)
                        .padding(top = 20.dp, bottom = 20.dp)
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                Text(text = book.title, fontSize = 30.sp)

                Info(book)
            }

        }

        Synopsis(book)
    }
}

@Composable
fun PortraitBookDisplay(book : Book)
{
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
            AsyncImage(
                model = book.cover,
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .padding(top = 20.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Info(book)
            Synopsis(book)
        }
    }
}

@Composable
private fun PriceAdQty(
    book: Book,
) {
    var qty by remember { mutableStateOf(TextFieldValue("1")) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = book.price + " €", fontSize = 30.sp)

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
}

@Composable
fun Info(book: Book) {
    PriceAdQty(book)

    AddToCart()
}

@Composable
fun Synopsis(book: Book) {
    Column() {
        Text(text = stringResource(R.string.synopsis), fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        book.synopsis.forEach {
            Text(
                text = it,
                textAlign = TextAlign.Justify,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun AddToCart() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp)
    ) {
        Button(
            onClick = {},
        ) {
            Text(text = stringResource(id = R.string.add_to_cart))
        }
    }
}