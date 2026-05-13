package com.example.cookai.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.cookai.data.local.UserDao
import com.example.cookai.ui.ai.AiChefScreen
import com.example.cookai.ui.auth.RegisterScreen
import com.example.cookai.ui.home.HomeScreen
import com.example.cookai.ui.navigation.BottomBar
import com.example.cookai.ui.profille.ProfileScreen
import com.example.cookai.ui.recipes.RecipeDetailScreen
import com.example.cookai.ui.recipes.RecipeListScreen
import com.example.cookai.viewModel.RecipeViewModel
import com.example.cookai.viewModel.RecipeViewModelFactory

@Composable
fun AppNavigation(userDao: UserDao) {
    val navController = rememberNavController()
    val viewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(userDao)
    )

    if (viewModel.user == null) {
        RegisterScreen { name ->
            viewModel.registerUser(name)
        }

    } else
        Scaffold(
            bottomBar = {
                BottomBar(navController)
            }
        ) { padding ->


            NavHost(
                navController = navController,
                startDestination = Routes.HOME,
                modifier = Modifier.padding(padding)
            ) {
                composable(Routes.HOME) {
                    HomeScreen(navController, viewModel)
                }
                composable(Routes.RECIPES) {
                    RecipeListScreen(navController, viewModel)
                }
                composable(
                    route = "recipe_detail/{id}"
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0

                    RecipeDetailScreen(
                        recipeId = id,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable(Routes.PROFILE) {
                    ProfileScreen(navController, viewModel)
                }
                composable(Routes.AI_CHEF){
                    AiChefScreen(navController, viewModel)
                }
            }
        }
}