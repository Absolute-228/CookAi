package com.example.cookai.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.node.DelegatableNode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookai.data.local.UserDao
import com.example.cookai.data.local.UserEntity
import com.example.cookai.data.model.Recipe
import kotlinx.coroutines.launch
import com.example.cookai.data.model.Achievement

class RecipeViewModel(private val userDao: UserDao): ViewModel() {

    var user by mutableStateOf<UserEntity?>(null)
        private set
    var cookedCount by mutableStateOf(0)
        private set
    var achievements by mutableStateOf(
        listOf(
            Achievement("Первый шаг", "Приготовить 1 блюдо", false),
            Achievement("Любитель кухни", "Приготовить 5 блюд", false),
            Achievement("Почти шеф", "Приготовить 10 блюд", false)
        )
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

    fun registerUser(name: String){
        val newUser = UserEntity(
            id = 0,
            name = name,
            xp = 0,
            level = "Новичок",
            cookedCount = 0
        )
        user = newUser

        viewModelScope.launch {
            userDao.insertUser(newUser)
        }
    }

    fun addXp(xp: Int){
        user?.let { currentUser ->
            val newXp = currentUser.xp + xp

            val updatedUser = currentUser.copy(
                xp = currentUser.xp + xp,
                level = calculateLevel(currentUser.xp + xp),
                cookedCount = currentUser.cookedCount + 1
            )
            user = updatedUser
            checkAchievements(updatedUser.cookedCount)
            saveUser(updatedUser)
        }
    }

    fun calculateLevel(xp: Int): String{
        return when{
            xp >= 100 -> "Шеф"
            xp >= 50 -> "Любитель"
            else -> "Новичок"
        }
    }

    private fun updateLevel(xp: Int): String{
        return  when {
            xp >= 100 -> "Шеф"
            xp >= 50 -> "Повар"
            else -> "Новичок"
        }
    }


    init {
        loadUser()
    }

    private fun loadUser(){
        viewModelScope.launch {
            val savedUser = userDao.getUser()

            user = savedUser
        }
    }


    private fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun getProgress(): Float{
        val xp = user?.xp ?: 0
        return when{
            xp>= 100 -> 1f
            xp >= 50 -> (xp - 50) / 50f
            else -> xp / 50f
        }
    }


    private fun checkAchievements(cookedCount: Int){
        achievements = achievements.map {
            when(it.title){
                "Первый шаг" -> it.copy(unlocked = cookedCount >=1)
                "Любитель кухни" -> it.copy(unlocked = cookedCount >=5)
                "Почти шеф" -> it.copy(unlocked = cookedCount >= 10)
                else -> it
            }
        }
    }

}