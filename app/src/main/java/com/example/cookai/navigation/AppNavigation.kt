package com.example.cookai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.cookai.ui.home.HomeScreen
import com.example.cookai.ui.recipes.RecipeDetailScreen
import com.example.cookai.ui.recipes.RecipeListScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
    ){
        composable(Routes.HOME){
            HomeScreen(navController)
        }
        composable(Routes.RECIPES){
            RecipeListScreen(navController)
        }
        composable(
            route = "recipe_detail/{id}",
        ){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0

            RecipeDetailScreen(
                recipeId = id,
                navController = navController
            )
        }
    }
}