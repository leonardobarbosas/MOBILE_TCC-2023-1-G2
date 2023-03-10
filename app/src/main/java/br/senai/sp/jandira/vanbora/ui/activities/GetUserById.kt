package br.senai.sp.jandira.vanbora.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.ui.activities.ui.ui.theme.VanboraTheme

class GetUserById : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetId()
                }
            }
        }
    }
}

@Composable
fun GetId() {

    var idState by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Busque o Id"
            )
            OutlinedTextField(
                value = idState,
                onValueChange = {
                    idState = it
                },
                label = {
                    Text(text = "Insira o Id")
                }
            )
        }

        Column {
            Card() {
                Text(text = "a")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VanboraTheme {
        GetId()
    }
}