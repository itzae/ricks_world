package com.itgonca.ricksworld.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.itgonca.ricksworld.ui.screens.detail.CharacterDetailScreenRoute
import com.itgonca.ricksworld.ui.screens.home.HomeScreenRoute

@Composable
fun MainGraph(navHostController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreenRoute { navHostController.navigate("character_detail/$it") }
        }
        composable(
            "character_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val idCharacter = it.arguments?.getString("id") ?: ""
            CharacterDetailScreenRoute(idCharacter = idCharacter) {
                navHostController.navigateUp()
            }
        }
    }
}