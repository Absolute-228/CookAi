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
import com.example.cookai.navigation.Routes

@Composable
fun HomeScreen(navController: NavController, viewModel: RecipeViewModel){

    val user = viewModel.user
    val progress = viewModel.getProgress()

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


        Spacer(modifier = Modifier.height(10.dp))
        Text("Прогресс уровня")
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text("Достижения")
        viewModel.achievements.forEach { achievement ->
            Text(
                text = if (achievement.unlocked)
                "✅ ${achievement.title}"
                else
                    "🔒 ${achievement.title}"
            )
        }



        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("recipes")
        }) {Text("Перейти к рецептам") }


        Button(onClick = {
            navController.navigate(Routes.PROFILE)
        }) {
            Text("Профиль")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Routes.AI_CHEF)
        }) { Text("AI-шеф \uD83E\uDD16")}

    }
}