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

    var achievements by mutableStateOf(
        listOf(
            Achievement("Первый шаг", "Приготовить 1 блюдо", false),
            Achievement("Любитель кухни", "Приготовить 5 блюд", false),
            Achievement("Почти шеф", "Приготовить 10 блюд", false)
        )
    )
    private val recipes = listOf(
        Recipe(
            id = 1,
            title = "Омлет",
            description = "Простой омлет на завтрак",
            ingredients = listOf("Яйца", "Молоко", "Соль", "Сливочное масло"),
            steps = listOf(
                "Разбей яйца в миску",
                "Добавь молоко и соль",
                "Хорошо перемешай",
                "Разогрей сковороду",
                "Обжарь омлет 3–4 минуты"
            ),
            difficulty = "Легко",
            xp = 10,
            cookingTime = 10,
            calories = 250,
            tips = listOf(
                "Не включай слишком сильный огонь",
                "Чтобы омлет был мягким, не пережаривай его"
            )
        ),
        Recipe(
            id = 2,
            title = "Овощной салат",
            description = "Лёгкий салат из свежих овощей",
            ingredients = listOf("Помидоры", "Огурцы", "Соль", "Оливковое масло"),
            steps = listOf(
                "Помой овощи",
                "Нарежь помидоры и огурцы",
                "Добавь соль",
                "Заправь маслом",
                "Перемешай"
            ),
            difficulty = "Легко",
            xp = 8,
            cookingTime = 7,
            calories = 120,
            tips = listOf(
                "Солить лучше перед подачей",
                "Можно добавить зелень для вкуса"
            )
        ),
        Recipe(
            id = 3,
            title = "Паста с сыром",
            description = "Простая паста с сыром",
            ingredients = listOf("Паста", "Сыр", "Соль", "Сливочное масло"),
            steps = listOf(
                "Вскипяти воду",
                "Добавь соль",
                "Отвари пасту 8–10 минут",
                "Слей воду",
                "Добавь масло и сыр"
            ),
            difficulty = "Средне",
            xp = 20,
            cookingTime = 15,
            calories = 420,
            tips = listOf(
                "Не переваривай пасту",
                "Оставь немного воды от пасты для соуса"
            )
        ),
        Recipe(
            id = 4,
            title = "Куриный суп",
            description = "Домашний суп с курицей",
            ingredients = listOf("Курица", "Картофель", "Морковь", "Лук", "Соль"),
            steps = listOf(
                "Промой курицу",
                "Поставь воду на огонь",
                "Добавь курицу",
                "Нарежь овощи",
                "Вари суп 35–40 минут"
            ),
            difficulty = "Средне",
            xp = 30,
            cookingTime = 45,
            calories = 350,
            tips = listOf(
                "Снимай пену при варке",
                "Добавляй соль ближе к концу приготовления"
            )
        ),
        Recipe(
            id = 5,
            title = "Блины",
            description = "Домашние тонкие блины",
            ingredients = listOf("Мука", "Яйца", "Молоко", "Сахар", "Соль"),
            steps = listOf(
                "Смешай яйца, молоко и сахар",
                "Добавь муку",
                "Перемешай до однородности",
                "Разогрей сковороду",
                "Жарь блины по 1–2 минуты с каждой стороны"
            ),
            difficulty = "Средне",
            xp = 25,
            cookingTime = 25,
            calories = 500,
            tips = listOf(
                "Тесто должно быть без комочков",
                "Сковороду лучше хорошо разогреть перед первым блином"
            )
        ),
        Recipe(
            id = 6,
            title = "Рис с овощами",
            description = "Полезное блюдо на каждый день",
            ingredients = listOf("Рис", "Морковь", "Перец", "Лук", "Соль"),
            steps = listOf(
                "Промой рис",
                "Отвари рис",
                "Нарежь овощи",
                "Обжарь овощи",
                "Смешай рис с овощами"
            ),
            difficulty = "Легко",
            xp = 15,
            cookingTime = 20,
            calories = 300,
            tips = listOf(
                "Промывай рис до прозрачной воды",
                "Овощи лучше не пережаривать"
            )
        ),
        Recipe(
            id = 7,
            title = "Жареный картофель",
            description = "Классический жареный картофель",
            ingredients = listOf("Картофель", "Масло", "Соль", "Лук"),
            steps = listOf(
                "Очисти картофель",
                "Нарежь ломтиками",
                "Разогрей масло",
                "Жарь картофель 15–20 минут",
                "Добавь соль в конце"
            ),
            difficulty = "Легко",
            xp = 12,
            cookingTime = 25,
            calories = 430,
            tips = listOf(
                "Не мешай картофель слишком часто",
                "Соль добавляй в конце, чтобы картофель был хрустящим"
            )
        ),
        Recipe(
            id = 8,
            title = "Сырные тосты",
            description = "Быстрые горячие тосты",
            ingredients = listOf("Хлеб", "Сыр", "Масло"),
            steps = listOf(
                "Намажь хлеб маслом",
                "Положи сыр",
                "Разогрей сковороду",
                "Обжарь тосты до золотистой корочки"
            ),
            difficulty = "Легко",
            xp = 5,
            cookingTime = 8,
            calories = 280,
            tips = listOf(
                "Используй сыр, который хорошо плавится",
                "Не ставь слишком сильный огонь"
            )
        )
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



    init {
        loadUser()
    }

    private fun loadUser(){
        viewModelScope.launch {
            val savedUser = userDao.getUser()

            user = savedUser
            savedUser?.let {
                checkAchievements(it.cookedCount)
            }
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