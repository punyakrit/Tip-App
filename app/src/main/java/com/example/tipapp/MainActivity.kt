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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.theme.TipAppTheme
import java.text.NumberFormat
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
    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0


    var InputTip by remember {
        mutableStateOf("")
    }
    var tipPer = InputTip.toDoubleOrNull() ?:0.0

    var roundUp by remember {
        mutableStateOf(false)
    }

    val tip = calculateTip(amount, tipPer, roundUp)

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Calculate Tip",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(
            "Bill Amount",
            value = amountInput,
            onValueChange = { amountInput = it }
        )
        Spacer(Modifier.height(18.dp))

        EditNumberField(
            labelType = "Enter tip percent",
            value = InputTip,
            onValueChange = {InputTip = it})

        Spacer(Modifier.height(12.dp))

        RoundTheTipRow(roundUp = roundUp, onRoundUp ={roundUp = it} )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Your Tip is "+ tip,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditNumberField(labelType: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelType) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)
        ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType
            .Number
            , imeAction = ImeAction.Next
        ),
    )
}


@Composable
fun RoundTheTipRow(
    roundUp :Boolean,
    onRoundUp: (Boolean) ->Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .size(48.dp)
    ) {
        Text("Round Up Tip?")
        Switch(checked = roundUp, onCheckedChange = onRoundUp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End))
    }
}


private fun calculateTip(
    amount: Double,
    tipPercent: Double,
roundUp: Boolean
) :String{
var tip = tipPercent/100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)

    return NumberFormat.getCurrencyInstance().format(tip)

}