package com.example.cookai.ui.profille

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookai.data.local.UserEntity
import com.example.cookai.viewModel.RecipeViewModel
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.getValue

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: RecipeViewModel
) {
    val user = viewModel.user
    val achievements = viewModel.achievements
    val animatedProgress by animateFloatAsState(
        targetValue = viewModel.getProgress()
    )

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "\uD83D\uDC64 ${user?.name}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(Modifier.height(8.dp))
                Text("Уровень: ${user?.level}", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("XP: ${user?.xp}", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Прогресс уровня")
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        achievements.forEach { achievement ->
            Card(Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    Modifier
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = achievement.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = if (achievement.unlocked) "✅" else "\uD83D\uDD12"
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Назад")
        }
    }
}
