package com.example.cookai.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookai.viewModel.RecipeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeViewModel){

    val user = viewModel.user

    Column(Modifier.padding(16.dp).fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ->
        Text(
            text = "CookAi",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(20.dp))

        Text("Уровень: ${user.level}")
        Text("XP: ${user.xp}")

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("recipes")
        }) {Text("Перейти к рецептам") }





    }
}