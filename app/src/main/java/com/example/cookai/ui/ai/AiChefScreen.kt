package com.example.cookai.ui.ai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookai.viewModel.RecipeViewModel

@Composable
fun AiChefScreen(
    navController: NavController,
    viewModel: RecipeViewModel
) {
    var ingredientsText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "AI-шеф 🤖",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Укажите продукты, которые есть дома",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = ingredientsText,
            onValueChange = { text ->
                ingredientsText = text
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Например: яйца, сыр, помидоры")
            },
            minLines = 3
        )

        Button(
            onClick = {
                viewModel.generateAiRecipe(ingredientsText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Подобрать рецепт")
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Text(
                text = if (viewModel.aiResult.isBlank())
                    "Введите продукты, и AI-шеф подберёт рецепт"
                else
                    viewModel.aiResult,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}