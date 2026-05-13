package com.example.cookai.ui.recipes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ){
        AnimatedVisibility(
            visible = true,
            enter = fadeIn() + slideInVertically(initialOffsetY = {it / 4})
        ) {
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
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically(initialOffsetY = {it / (index + 2)})
            ) {
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

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val buttonScale by animateFloatAsState(
            targetValue = if (isPressed) 0.96f else 1f,
            label = "cook_button_scale"
        )
        Button(onClick = {
            viewModel.addXp(recipe.xp)
            navController.navigate(Routes.HOME){
                popUpTo("home"){inclusive = true}
            }
        },
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer{
                    scaleX = buttonScale
                    scaleY = buttonScale
                }
        ) {
            Text("Я приготовил")
        }
    }

}