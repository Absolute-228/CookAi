package com.example.cookai.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

data class AiRequest(
    val ingredients: String,
    val preferences: String
)

data class AiResponce(
    val result : String
)

interface AiApi{

    @POST("/ai/recipe")
    suspend fun generateRecipe(
        @Body request: AiRequest
    ): AiResponce

}