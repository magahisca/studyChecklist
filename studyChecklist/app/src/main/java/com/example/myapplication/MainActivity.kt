package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val checkBox1 = findViewById<CheckBox>(R.id.checkbox1)as CheckBox
        val checkBox2 = findViewById<CheckBox>(R.id.checkbox2)as CheckBox
        val checkBox3 = findViewById<CheckBox>(R.id.checkbox3)as CheckBox
        val checkBox4 = findViewById<CheckBox>(R.id.checkbox4)as CheckBox
        val checkBox5 = findViewById<CheckBox>(R.id.checkbox5)as CheckBox
        val checkBox6 = findViewById<CheckBox>(R.id.checkbox6)as CheckBox

        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Nigga")
    }
}