package br.senai.sp.jandira.vanbora.components.forms.transport

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R


@Composable
fun VanInfos(){
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



    //Main
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.25f).background(Color.Blue),onClick = { /*TODO*/ }) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(start = 80.dp)) {
                    Icon(imageVector = Icons.Filled.PhotoCamera, contentDescription = "icone camera", modifier = Modifier.fillMaxSize(0.2f))
                }
                Image(imageVector = Icons.Filled.DirectionsBus, contentDescription = "icone pararepresentar a van", modifier = Modifier.fillMaxSize())

            }

        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = placaVan, onValueChange = {placaVan = it}, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.placa_van),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    if (isPlacaVanError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isPlacaVanError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                ))
            OutlinedTextField(value = modeloVan, onValueChange = {modeloVan = it}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                    text = stringResource(id = R.string.modelo_van),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                    )
                },
                trailingIcon = {
                    if (isModeloVanError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isModeloVanError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                ))
            OutlinedTextField(value = vagasVan, onValueChange = {vagasVan = it}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.numero_vagas),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    if (isVagasVanError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isVagasVanError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                ))
        }
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