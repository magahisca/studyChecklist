package com.example.testapp2
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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
    //to add: more items and add terrain
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


