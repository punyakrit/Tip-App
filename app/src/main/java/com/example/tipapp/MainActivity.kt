package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.theme.TipAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipAppTheme {
                TipScreen(

                )

            }
        }
    }
}

@Preview
@Composable
fun TipScreen(){
    Box (
        modifier = Modifier.background(Color.White)
            ){
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Calculate Tip",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            EditNumber()

            Spacer(modifier = Modifier.height(30.dp))

            Text("amount ")
        }
    }

}

@Composable
fun EditNumber(){
    var amountInput by remember { mutableStateOf("") }

    TextField(
        value = amountInput,
        onValueChange ={amountInput = it},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        label = {Text("Bill Amount")}
        ,modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)

    )
}


private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0
) {
}