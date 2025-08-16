package com.example.testapp2


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameHud(gameState: GameState) {
    Text("\uD83D\uDCB5 ${gameState.coins}", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(50.dp))
    Text("Level: ${gameState.playerLevel}", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(50.dp))
    Text("Exp: ${gameState.currentExp}/ ${gameState.reqExp}", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(50.dp))
    Text("Pet Health: ${gameState.pet.health}", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(20.dp))
    Text("Pet State: ${gameState.pet.petState}", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(20.dp))
    Text("${gameState.pet.type.typeName}", style = MaterialTheme.typography.headlineMedium)
}