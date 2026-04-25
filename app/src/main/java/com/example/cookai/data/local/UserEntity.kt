package com.example.cookai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val xp: Int,
    val level: String,
    val cookedCount: Int
)