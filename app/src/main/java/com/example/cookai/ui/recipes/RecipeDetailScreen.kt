package com.example.cookai.ui.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookai.navigation.Routes
import com.example.cookai.viewModel.RecipeViewModel

@Composable
fun RecipeDetailScreen(recipeId: Int, navController: NavController, viewModel: RecipeViewModel) {

    val recipe = viewModel.getRecipeById(recipeId)

    if (recipe == null){
        Text("Рецепт не найден")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "\uD83C\uDF7D\uFE0F ${recipe.title}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${recipe.description}",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "⏱ ${recipe.cookingTime} мин • \uD83D\uDD25 ${recipe.calories} ккал • +${recipe.xp} XP",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "Сложность: ${recipe.difficulty}",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Ингредиенты",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))

        recipe.ingredients.forEach { ingredient ->
            Text("• $ingredient")
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Шаги приготовления",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        recipe.steps.forEachIndexed { index, step ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = "Шаг ${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(step)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Советы",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        recipe.tips.forEach { tip ->
            Text("💡 $tip")
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            viewModel.addXp(recipe.xp)
            navController.navigate(Routes.HOME){
                popUpTo("home"){inclusive = true}
            }
        },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Я приготовил")
        }
    }

}