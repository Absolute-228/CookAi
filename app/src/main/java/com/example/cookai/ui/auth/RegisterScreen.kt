package com.example.cookai.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onRegister: (String)-> Unit){
    var name by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Text("Введите имя:")
        TextField(
            value = name,
            onValueChange = {name = it}
        )
        Button(onClick = {
            if(name.isNotBlank()){
                onRegister(name)
            }
        }) {Text("Начать") }
    }
}