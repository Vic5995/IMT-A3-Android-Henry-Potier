package com.imt.andriamparivonylenglart


import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.imt.andriamparivonylenglart.presentation.detail.BookViewModel
import com.imt.andriamparivonylenglart.presentation.detail.DetailScreen
import com.imt.andriamparivonylenglart.presentation.library.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{book}",
            arguments = listOf(
                navArgument("book") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->

            val bookId = entry.arguments?.getString("book")

            bookId?.let { DetailScreen(BookViewModel(bookId))  }
        }
    }
}
