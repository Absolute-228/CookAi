package com.example.cookai.viewModel

import androidx.lifecycle.ViewModel
import com.example.cookai.data.model.Recipe

class RecipeViewModel: ViewModel() {
    private val recipes = listOf(
        Recipe(1, "Омлет","Простой омлет", listOf("Яйца", "Молоко", "Сливочное масло"), listOf("Разбейте яйцо","Перемешай","Обжарить"),"Легко", 10 ),
        Recipe(2, "Салат", "Овощной салат", listOf("Помидоры","Огурцы","Соль"), listOf("Нарежь овощи","Перемешай"),"Легко", 8 ),
    )
    fun getRecipes(): List<Recipe>{
        return recipes
    }
    fun getRecipeById(id: Int): Recipe?{
        return recipes.find { it.id == id }
    }

}