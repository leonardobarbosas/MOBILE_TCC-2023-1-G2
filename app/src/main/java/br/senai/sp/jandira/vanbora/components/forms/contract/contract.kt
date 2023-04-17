package br.senai.sp.jandira.vanbora.components.forms.contract

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.activities.client.EnviarContratoActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnviarContrato() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current

        var nomeResponsavelState by rememberSaveable() {
            mutableStateOf("")
        }

        var isNomeResponsavelError by remember() {
            mutableStateOf(false)
        }

        var nomePassageiroState by rememberSaveable() {
            mutableStateOf("")
        }

        var isNomePassageiroError by remember() {
            mutableStateOf(false)
        }

        var idadePassageiroState by rememberSaveable() {
            mutableStateOf("")
        }

        var isIdadePassageiroError by remember() {
            mutableStateOf(false)
        }

        var escolaState by rememberSaveable() {
            mutableStateOf("")
        }

        var isEscolaError by remember() {
            mutableStateOf(false)
        }

        //Tipo Transporte
        var mExpanded by remember { mutableStateOf(false) }
        val selects = listOf(
            stringResource(id = R.string.ida),
            stringResource(id = R.string.volta),
            stringResource(id = R.string.ida_e_volta)
        )
        var mSelectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        //Tipo Pagamento
        var nExpanded by remember { mutableStateOf(false) }
        val nSelects = listOf(
            stringResource(id = R.string.diario),
            stringResource(id = R.string.semanal),
            stringResource(id = R.string.mensal),
            stringResource(id = R.string.anual)

        )
        var nSelectedText by remember { mutableStateOf("") }
        var nTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val nIcon = if (nExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown


        //Nome Responsavel
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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


        //Nome Passageiro
        OutlinedTextField(
            value = nomePassageiroState, onValueChange = {
                nomePassageiroState = it

                if (it == "" || it == null) {
                    isNomePassageiroError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.nome_passageiro),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isNomePassageiroError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isNomePassageiroError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isNomePassageiroError) {
            Text(
                text = stringResource(id = R.string.nome_passageiro_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }


        //Idade Passageiro
        OutlinedTextField(
            value = idadePassageiroState, onValueChange = {
                idadePassageiroState = it

                if (it == "" || it == null) {
                    isIdadePassageiroError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.idade_passageiro),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isIdadePassageiroError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isIdadePassageiroError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isIdadePassageiroError) {
            Text(
                text = stringResource(id = R.string.idade_passageiro_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        //Tipo de transporte
        Column() {
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    }
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        "Tipo de transporte",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                selects.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

        //Tipo de transporte
        Column() {
            OutlinedTextField(
                value = nSelectedText,
                onValueChange = { nSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        nTextFieldSize = coordinates.size.toSize()
                    }
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        "Escola",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    Icon(nIcon, "contentDescription",
                        Modifier.clickable { nExpanded = !nExpanded })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            DropdownMenu(
                expanded = nExpanded,
                onDismissRequest = { nExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { nTextFieldSize.width.toDp() })
            ) {
                nSelects.forEach { label ->
                    DropdownMenuItem(onClick = {
                        nSelectedText = label
                        nExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

        //Tipo de transporte
        Column() {
            OutlinedTextField(
                value = nSelectedText,
                onValueChange = { nSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        nTextFieldSize = coordinates.size.toSize()
                    }
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        "Tipo de pagamento",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    Icon(nIcon, "contentDescription",
                        Modifier.clickable { nExpanded = !nExpanded })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            DropdownMenu(
                expanded = nExpanded,
                onDismissRequest = { nExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { nTextFieldSize.width.toDp() })
            ) {
                nSelects.forEach { label ->
                    DropdownMenuItem(onClick = {
                        nSelectedText = label
                        nExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, MotoristasActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }


    }
}

