package com.example.testapp2

//wla pa to to be refactored
fun brushFunction(gameState: GameState): GameState {
    var newState = gameState
    val pet = gameState.pet

    newState = when {
        pet.health in 92.0..99.9 -> newState.copy(
            pet = pet.copy(health = (pet.health + 1).coerceAtMost(pet.maxHealth))
        )
        pet.health in 89.0..92.0 -> newState.copy(
            pet = pet.copy(health = (pet.health + 2).coerceAtMost(pet.maxHealth))
        )
        pet.health in 75.0..88.0 -> newState.copy(
            pet = pet.copy(health = (pet.health + 3).coerceAtMost(pet.maxHealth))
        )
        else -> newState.copy(
            pet = pet.copy(health = (pet.health + 5).coerceAtMost(pet.maxHealth))
        )
    }

    newState = newState.copy(
        coins = newState.coins + 5,
        currentExp = newState.currentExp + (5 + (newState.playerLevel - 0))
    )

    if (newState.currentExp >= newState.reqExp) {
        newState = newState.copy(
            playerLevel = newState.playerLevel + 1,
            reqExp = newState.reqExp * newState.playerLevel
        )
    }

    return newState
}