package com.example.testapp2

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun HealthDecayLoop(gameState: GameState, onUpdate: (GameState) -> Unit) {
    LaunchedEffect(Unit) {
        while (true) {
            delay(250)
            if (gameState.pet.health > 0) {
                var newPet = gameState.pet.copy(
                    health = (gameState.pet.health - 0.00008).coerceAtLeast(0.0)
                )

                val newState = when (newPet.health) {
                    in 0.0..19.0 -> newPet.copy(petState = "Very Poor")
                    in 20.0..40.0 -> newPet.copy(petState = "Poor")
                    in 41.0..59.0 -> newPet.copy(petState = "Fair")
                    in 60.0..89.0 -> newPet.copy(petState = "Good")
                    in 90.0..100.0 -> newPet.copy(petState = "Very Good")
                    else -> newPet
                }

                onUpdate(gameState.copy(pet = newState))
            }
        }
    }
}