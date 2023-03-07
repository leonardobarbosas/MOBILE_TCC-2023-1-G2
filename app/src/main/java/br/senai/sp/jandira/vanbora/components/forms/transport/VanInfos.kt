import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R

@Composable
fun VanInfos(context: Context) {

    var placaVan by rememberSaveable {
        mutableStateOf("")
    }
    var modeloVan by rememberSaveable {
        mutableStateOf("")
    }
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

    val scrollState = rememberScrollState()

    //IMAGE
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .padding(start = 80.dp, end = 80.dp)
            .background(Color(156, 156, 156, 255))
            .clickable {
                Toast
                    .makeText(context, "Quem ler é gay kkkk", Toast.LENGTH_SHORT)
                    .show()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.PhotoCamera,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }

    //OUTLINED
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        OutlinedTextField(
            value = placaVan,
            onValueChange = {
                placaVan = it
                if (it == "" || it == null) {
                    isPlacaVanError
                }
            },
            modifier = Modifier.padding(bottom = 20.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.placa_van)
                )
            },
            trailingIcon = {
                if (isPlacaVanError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isPlacaVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = modeloVan,
            onValueChange = {
                modeloVan = it
                if (it == "" || it == null) {
                    isModeloVanError
                }
            },
            modifier = Modifier.padding(bottom = 20.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.modelo_van)
                )
            },
            trailingIcon = {
                if (isModeloVanError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isModeloVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = vagasVan,
            onValueChange = {
                vagasVan = it
                if (it == "" || it == null) {
                    isVagasVanError
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.numero_vagas)
                )
            },
            trailingIcon = {
                if (isVagasVanError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isVagasVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    //BUTTON
    Column() {
        Button(
            onClick = {
                isPlacaVanError = placaVan.length == 0
                isModeloVanError = modeloVan.length == 0
                isVagasVanError = vagasVan.length == 0
                      },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }
    }
}