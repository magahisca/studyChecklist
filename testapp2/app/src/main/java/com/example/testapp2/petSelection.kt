package com.example.testapp2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


data class PetType(
    val typeName:String
)

val petOptions = listOf(
    PetType("Dog"),
    PetType("Cat"),
    PetType("Bug")

)

@Composable
fun PetSelection (onPetSelected: (PetType) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.95f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){Text("Choose your Pet!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        petOptions.forEach{pet ->
            Button(
            onClick = { onPetSelected(pet) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ){Text(pet.typeName)}
        }

    }

}
