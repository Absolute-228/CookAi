package com.example.cookai.ui.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookai.viewModel.RecipeViewModel


@Composable
fun RecipeListScreen(navController: NavController){
    val viewModel: RecipeViewModel = viewModel()
    val recipes = viewModel.getRecipes()

    LazyColumn(Modifier.padding(16.dp)
    ) {
        items(recipes) {recipe ->
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(text = recipe.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = recipe.description, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = {
                        navController.navigate("recipe_detail/${recipe.id}")
                    }){
                        Text("Открыть")
                    }
                }
            }


        }
    }

}