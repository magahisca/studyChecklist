package com.example.testapp2

val defaultPetType = PetType("Dog")
val startingPetState = "Fair"
data class Pet(
    var health: Double = 50.00,
    val maxHealth: Double = 100.00,
    val type: PetType = defaultPetType,
    val petState:String = startingPetState
)

data class GameState(
    val pet: Pet = Pet(),
    val coins: Int = 0,
    val playerLevel: Int = 1,
    val currentExp: Int = 0,
    val reqExp: Int = 10,
    val hasSelectedPet: Boolean = false

)