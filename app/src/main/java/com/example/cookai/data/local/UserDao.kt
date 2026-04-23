package com.example.cookai.data.local

import androidx.room.*

@Dao
interface UserDao{
    @Query("SELECT * FROM user WHERE id = 0")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}