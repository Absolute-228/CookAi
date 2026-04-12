package com.example.cookai.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val difficulty: String,
    val xp: Int
)