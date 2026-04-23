package com.example.cookai.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookai.data.local.UserDao

class RecipeViewModelFactory(private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(userDao) as T
    }
}