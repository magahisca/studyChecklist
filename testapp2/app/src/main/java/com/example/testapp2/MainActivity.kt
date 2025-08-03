package com.example.testapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp2.ui.theme.Testapp2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Testapp2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PetGame()
                }
            }
        }
    }
}
data class ShopItem(
    val id: Int,
    val name: String,
    val price: Int,
    //pwede ata dito mag insert ng path para sa icon or sprites
    val slot: String
)
val shopItems = listOf(
    ShopItem(1,"Hat", 5, "isHat") ,
    ShopItem(2 ,"Jacket", 5, "isClothes")
)
@Composable
fun ShopScreen(coins: Int,onBuy:(Int) -> Unit ,onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.95f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🛒 Welcome to the Shop!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("💰 Coins: $coins", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn{

            items(shopItems){ item -> ShopRow(item, coins, onBuy = onBuy)}
        }
        Button(onClick = onClose) {
            Text("Back to Game")
        }
    }
}
@Composable
fun ShopRow(item: ShopItem, coins: Int, onBuy:(Int) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp) ,
    horizontalArrangement = Arrangement.SpaceBetween){
        Text("${item.name} ${item.price}")
        Button(onClick = {onBuy(item.price) //wla pang ginagawa, minus plang ng coins sa price
                         },enabled = coins >= item.price){
            Text("Buy")
        }
    }

}

/*
@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    Testapp2Theme {
        ShopScreen("World")
    }
}
*/

val defaultPetType = PetType("Dog")
val startingPetState = "Fair"
data class Pet(
    var health: Int = 50,
    val maxHealth: Int = 100,
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
@Composable
fun PetGame (){
    var gameState by remember {mutableStateOf(GameState())}
    var openShop by remember{mutableStateOf(false) }
    var petSelected by remember{mutableStateOf(false)}

    if(!petSelected){
        PetSelection(onPetSelected = { selectedType ->
            val newPet = Pet(type = selectedType)
            gameState = gameState.copy(pet = newPet)
            petSelected = true
        })
    }
if(petSelected) {
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            if (gameState.pet.health > 0) {
                gameState = gameState.copy(pet = gameState.pet.copy(health = gameState.pet.health - 1))
                if (gameState.pet.health in 0..19){
                    gameState = gameState.copy(pet = gameState.pet.copy(petState = "Very Poor"))
                }
                if (gameState.pet.health in 20..40){
                    gameState = gameState.copy(pet = gameState.pet.copy(petState = "Poor"))
                }
                if (gameState.pet.health in 41..59){
                    gameState = gameState.copy(pet = gameState.pet.copy(petState = "Fair"))
                }
                if (gameState.pet.health in 60..89){
                    gameState = gameState.copy(pet = gameState.pet.copy(petState = "Good"))
                }
                if (gameState.pet.health in 90..100){
                    gameState = gameState.copy(pet = gameState.pet.copy(petState = "Very Good"))
                }
            }
        }
    }
}
    Column(
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text("\uD83D\uDCB5 ${gameState.coins}",style = MaterialTheme.typography.headlineMedium )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Level: ${gameState.playerLevel}",style = MaterialTheme.typography.headlineMedium )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Exp: ${gameState.currentExp}/ ${gameState.reqExp}",style = MaterialTheme.typography.headlineMedium )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Pet Health: ${gameState.pet.health}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Pet Health: ${gameState.pet.petState}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("${gameState.pet.type.typeName}", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (gameState.pet.health < gameState.pet.maxHealth) {
                gameState = gameState.copy(pet = gameState.pet.copy(health = (gameState.pet.health + 10).coerceAtMost(gameState.pet.maxHealth)))
                gameState = gameState.copy(coins = gameState.coins + 5)
                gameState = gameState.copy(currentExp = gameState.currentExp + (5 + (gameState.playerLevel - 0)))
            }
            if (gameState.currentExp >= gameState.reqExp){
                gameState = gameState.copy(playerLevel = gameState.playerLevel +1)
                gameState = gameState.copy(reqExp = gameState.reqExp * gameState.playerLevel)
            }
        }) {
            Text("Toothbrush")
        }

        Button(onClick = {openShop = true}){Text("\uD83D\uDED2 Shop")}
    }
    if (openShop){
        ShopScreen(
            coins = gameState.coins,
            onBuy = {price ->
                if (gameState.coins >= price){
                    gameState = gameState.copy(coins = gameState.coins - price)
                }
            },
            onClose = {openShop = false}
        )
    }
}



