package br.senai.sp.jandira.vanbora.components.forms.localize

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun Localizese () {

    val context = LocalContext.current

    var cepState by rememberSaveable {
        mutableStateOf("")
    }

    var isCepError by remember {
        mutableStateOf(false)
    }

    var numeroCasaState by rememberSaveable {
        mutableStateOf("")
    }

    var isNumeroCasaError by remember {
        mutableStateOf(false)
    }

    var complementosState by rememberSaveable {
        mutableStateOf("")
    }

    var isComplementosError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.vanbora_title1),
                fontSize = 32.sp,
                style = MaterialTheme.typography.body1,
            )

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        //CEP
        OutlinedTextField(
            value = cepState,
            onValueChange = {
                cepState = it

                if (it == "" || it == null) {
                    isCepError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp),
            label = {
                Text(text = stringResource(id = R.string.cep))
            },
            trailingIcon = {
                if (isCepError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isCepError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        //NMR DA CASA
        OutlinedTextField(
            value = numeroCasaState,
            onValueChange = {
                numeroCasaState = it

                if (it == "" || it == null) {
                    isNumeroCasaError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp),
            label = {
                Text(text = stringResource(id = R.string.nmr))
            },
            trailingIcon = {
                if (isNumeroCasaError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isNumeroCasaError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        //Complementos
        OutlinedTextField(
            value = complementosState,
            onValueChange = {
                complementosState = it

                if (it == "" || it == null) {
                    isComplementosError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp),
            label = {
                Text(text = stringResource(id = R.string.complementos))
            },
            trailingIcon = {
                if (isComplementosError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isComplementosError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(22.dp)
        ) {
            val senai = LatLng(-23.52867796490993, -46.89841654231567)

            val properties by remember {
                mutableStateOf(MapProperties(mapType = MapType.HYBRID))
            }
            val uiSettings by remember {
                mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
            }

            GoogleMap(
                modifier = Modifier.fillMaxWidth(),
                properties = properties,
                uiSettings = uiSettings
            ) {
                Marker(
                    position = senai,
                    title = "SENAI JANDIRA"
                )
            }
        }

        Button(
            onClick = {
                isCepError = cepState.length == 0
                isNumeroCasaError = numeroCasaState.length == 0
                isComplementosError = complementosState.length == 0
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }

    }
}