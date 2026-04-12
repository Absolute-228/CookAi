package com.example.cookai.ui.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookai.viewModel.RecipeViewModel

@Composable
fun RecipeDetailScreen(recipeId: Int, navController: NavController
) {
    val ViewModel: RecipeViewModel = viewModel()
    val recipe = ViewModel.getRecipeById(recipeId)

Column(Modifier.padding(16.dp).fillMaxSize()) {
    recipe?.let {
        Text(text = it.title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text("Ингредиенты")
        it.ingredients.forEach { ingredient ->
            Text(
                text = "- $ingredient",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        Text("Шаги приготовления")
        it.steps.forEach { step ->
            Text(
                text = "- $step",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    } ?: Text("Рецепт не найден")
}
}