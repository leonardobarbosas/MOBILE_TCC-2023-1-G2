package br.senai.sp.jandira.vanbora.components.forms.localize

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Whatsapp
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
fun Localizese() {

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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Whatsapp,
                contentDescription = "wpp",
                tint = Color(9, 150, 12, 255)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Nosso Whats-app: ",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "+55 (11) 99402-6755",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "email",
                tint = Color(0, 0, 0, 255)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Nosso Email: ",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "nexusgamer@gmail.com",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "email",
                tint = Color(55, 106, 252, 255)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Nosso Telefone: ",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "+55 (11) 99402-6755",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
            )
        }



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


    }
}