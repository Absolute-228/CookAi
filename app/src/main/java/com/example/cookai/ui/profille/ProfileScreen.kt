package com.example.cookai.ui.profille

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookai.viewModel.RecipeViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: RecipeViewModel
){
    val user = viewModel.user
    val achievements = viewModel.achievements

    Column(Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        Text("Профиль", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        user?.let {
            Text("Имя: ${user.name}")
            Text("Уровень: ${user.level}")
            Text("XP: ${user.xp}")
        } ?: Text("Загрузка...")
        Spacer(Modifier.height(20.dp))

        Text("Достижения", style = MaterialTheme.typography.titleLarge)
        achievements.forEach {
            Text(
                text = if (it.unlocked)
                "✅ ${it.title}"
                else
                "\uD83D\uDD12 ${it.title}"
            )
        }
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Назад")
        }
    }

}