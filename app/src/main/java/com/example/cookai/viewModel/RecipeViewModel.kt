package com.example.cookai.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookai.data.local.UserDao
import com.example.cookai.data.local.UserEntity
import com.example.cookai.data.model.Recipe
import com.example.cookai.data.model.User
import kotlinx.coroutines.launch

class RecipeViewModel(private val userDao: UserDao): ViewModel() {

    var user by mutableStateOf(
        User("Игрок", 0, "Новичок")
    )
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

    fun addXp(xp: Int){
        user.xp += xp
        updateLevel()
        saveUser()
    }

    private fun updateLevel(){
        user.level = when {
            user.xp >= 100 -> "Шеф"
            user.xp >= 50 -> "Повар"
            else -> "Новичок"
        }
    }


    init {
        loadUser()
    }

    private fun loadUser(){
        viewModelScope.launch {
            val savedUser = userDao.getUser()
            if (savedUser != null) {
                user = user.copy(
                xp = savedUser.xp,
                level = savedUser.level
                )
            }
        }
    }


    private fun saveUser(){
        viewModelScope.launch {
            userDao.insertUser(
                UserEntity(
                    id = 0,
                    name = user.name,
                    xp = user.xp,
                    level = user.level
                )
            )
        }
    }

    fun getProgress(): Float{
        return when{
            user.xp >= 100 -> 1f
            user.xp >= 50 -> (user.xp - 50) / 50f
            else -> user.xp / 50f
        }
    }

}