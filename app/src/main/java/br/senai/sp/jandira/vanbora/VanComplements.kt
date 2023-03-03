package br.senai.sp.jandira.vanbora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme

class VanComplements : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current

        val selectActivy by remember {
            mutableStateOf(SelectActivity::class.java)
        }

        var placaVan by rememberSaveable {
            mutableStateOf("")
        }
        var modeloVan by rememberSaveable {
            mutableStateOf("")
        }

        //Lembrar de mudar para int quando fizer função
        var vagasVan by rememberSaveable {
            mutableStateOf("")
        }

        var isPlacaVanError by rememberSaveable {
            mutableStateOf(false)
        }
        var isModeloVanError by rememberSaveable {
            mutableStateOf(false)
        }
        var isVagasVanError by rememberSaveable {
            mutableStateOf(false)
        }

        HeaderSelectDriverComplement(context = context, componentActivity = selectActivy.newInstance())

        //Main
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = { /*TODO*/ }) {

            }
            OutlinedTextField(value = placaVan, onValueChange = {placaVan = it})
            OutlinedTextField(value = modeloVan, onValueChange = {modeloVan = it})
            OutlinedTextField(value = vagasVan, onValueChange = {vagasVan = it})
        }

        //Footer

        Button(
            onClick = {
                isPlacaVanError = placaVan.isEmpty()
                isModeloVanError = modeloVan.isEmpty()
                isVagasVanError = vagasVan.isEmpty()
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }

        
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        Greeting()
    }
}