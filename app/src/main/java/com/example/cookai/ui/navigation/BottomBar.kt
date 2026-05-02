package com.example.cookai.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cookai.navigation.Routes

@Composable
fun BottomBar(navController: NavController){
    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.HOME)},
            icon = { Text("🏠") },
            label = {Text("Главная")}
        )

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Routes.RECIPES)},
            icon = {Text("\uD83C\uDF72")},
            label = {Text("Рецепты")}
        )

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Routes.PROFILE)},
            icon = {Text("\uD83D\uDC64")},
            label = {Text("Профиль")}
        )
    }
}