package br.senai.sp.jandira.vanbora.components.forms.contract

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnviarContrato() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var nomeResponsavelState by rememberSaveable(){
            mutableStateOf("")
        }

        var isNomeResponsavelError by remember() {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = nomeResponsavelState, onValueChange = {
                nomeResponsavelState = it

                if (it == "" || it == null) {
                    isNomeResponsavelError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.nome_responsavel),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isNomeResponsavelError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isNomeResponsavelError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isNomeResponsavelError) {
            Text(
                text = stringResource(id = R.string.nome_responsavel_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

    }
}
