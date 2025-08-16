package com.example.testapp2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PetGame() {
    var gameState by remember { mutableStateOf(GameState()) }
    var openShop by remember { mutableStateOf(false) }
    var petSelected by remember { mutableStateOf(false) }
    var brushCheck by remember { mutableStateOf(brushCheck()) }

    if (!petSelected) {
        PetSelection(onPetSelected = { selectedType ->
            val newPet = Pet(type = selectedType)
            gameState = gameState.copy(pet = newPet)
            petSelected = true
        })
    }

    if (petSelected) {
        HealthDecayLoop(gameState) { updated -> gameState = updated }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // HUD
            GameHud(gameState)

            Spacer(modifier = Modifier.height(20.dp))

            // Actions
            Button(onClick = {
                if (isBrushTimeMorning || isBrushTimeNight) {
                    if (isBrushTimeMorning && brushCheck.morningCheck) return@Button
                    if (isBrushTimeNight && brushCheck.nightCheck) return@Button

                    gameState = brushFunction(gameState)
                    if (isBrushTimeMorning) brushCheck.morningCheck = true
                    if (isBrushTimeNight) brushCheck.nightCheck = true
                }
            }) {
                Text("Toothbrush")
            }

            Button(onClick = { openShop = true }) {
                Text("\uD83D\uDED2 Shop")
            }
        }

        if (openShop) {
            ShopScreen(
                coins = gameState.coins,
                onBuy = { price ->
                    if (gameState.coins >= price) {
                        gameState = gameState.copy(coins = gameState.coins - price)
                    }
                },
                onClose = { openShop = false }
            )
        }
    }
}
