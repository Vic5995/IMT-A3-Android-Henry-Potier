package com.imt.andriamparivonylenglart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.imt.andriamparivonylenglart.ui.theme.AndriamparivonyLenglartTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<LibraryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndriamparivonyLenglartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LibrarySize(viewModel = viewModel )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun LibrarySize(viewModel: LibraryViewModel) {

    val state = viewModel.state.observeAsState()
    val refreshCount by remember { mutableStateOf(1) }
    
    LaunchedEffect(key1 = refreshCount){
        viewModel.loadBooks()
    }

    Text(text = if (state.value?.isLoading == true) "Loading" else "There are ${state.value?.books?.size} books in your library! ")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndriamparivonyLenglartTheme {
        Greeting("Android")
    }
}