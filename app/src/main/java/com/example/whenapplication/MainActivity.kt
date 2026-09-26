package com.example.whenapplication

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whenapplication.ui.theme.WhenApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhenApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainLocation(Modifier.padding(innerPadding))
                }
            }
        }
    }

    var inputText by mutableStateOf("")
    var outputText by mutableStateOf("Ответ...")

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun MainLocation(modifier: Modifier = Modifier)
    {

        val mod = Modifier
            .padding(50.dp)
            .background(color = Color.Gray)
            .height(150.dp)


        Column(mod, verticalArrangement = Arrangement.spacedBy(20.dp))
        {
            InputTextAsk("Введите текст")
            InputField(inputText)
            OutputText(outputText)
        }
    }

    @Composable
    fun InputTextAsk(textValue : String)
    {
        Text(text = textValue)
    }

    @Composable
    fun OutputText(textValue : String)
    {
        Text(text = textValue)
    }



    @Composable
    fun InputField(textValue : String)
    {
        TextField(value = textValue,
            onValueChange = {handleInputChange(it)},
            placeholder = {Text("Введите символ")}
            )
    }

    fun handleInputChange(inputString : String)
    {
        if(inputString.length > 1)
            inputText = inputString.last().toString()
        else
            inputText = inputString

        if(inputText.length == 1){
            outputText = when(inputString)
            {
                in "0".."9" -> "Это цифра!"
                in setOf("&","#", "<", "-") -> "Это спец символ!"
                else -> "Непредусмотренный вариант"
            }
            return;
        }

        outputText = "..."
    }
}
