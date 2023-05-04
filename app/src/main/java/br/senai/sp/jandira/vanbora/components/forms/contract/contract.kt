package br.senai.sp.jandira.vanbora.components.forms.contract

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.TipoTransporteList
import br.senai.sp.jandira.vanbora.ui.activities.client.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EnviarContrato(){

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




        val tipoTransporteCall = GetFunctionsCall.getTipoTransporteCall().getAllContracts()


        var tipoTransportes by remember {
            mutableStateOf(TipoTransporteList(listOf()))
        }

        tipoTransporteCall.enqueue(object : Callback<TipoTransporteList>{
            override fun onResponse(
                call: Call<TipoTransporteList>,
                response: Response<TipoTransporteList>
            ) {
                Log.i("ds3m", "onResponse: ${response.body()!!}")
            }

            override fun onFailure(call: Call<TipoTransporteList>, t: Throwable) {
                Log.i("ds3m", "onFailure: ${t.message}")
            }
        } )
        

        //Tipo Transporte
        var mExpanded by remember { mutableStateOf(false) }
        val selects = listOf(
            tipoTransportes
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

        //Escola - Teste
        var escolaState by remember { mutableStateOf(false) }
        val escolaSelects = listOf(
            stringResource(id = R.string.escola),
            stringResource(id = R.string.escola_error),
        )
        var escolaSelectedText by remember { mutableStateOf("") }
        var escolaTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val escolaIcon = if (nExpanded)
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
                selects.map { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.toString()
                        mExpanded = false
                    }) {
                        Text(text = label.toString())
                    }
                }
            }
        }

        //Escola
        Column() {
            OutlinedTextField(
                value = escolaSelectedText,
                onValueChange = { escolaSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        escolaTextFieldSize = coordinates.size.toSize()
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
                    Icon(escolaIcon, "contentDescription",
                        Modifier.clickable { escolaState = !escolaState })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            DropdownMenu(
                expanded = escolaState,
                onDismissRequest = { escolaState = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { escolaTextFieldSize.width.toDp() })
            ) {
                escolaSelects.forEach { label ->
                    DropdownMenuItem(onClick = {
                        escolaSelectedText = label
                        escolaState = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

        //Tipo de pagamento
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

//                RegisterNewContract(
//                    nomeResponsavel = nomeResponsavelState,
//                    nomePassageiro = nomeResponsavelState,
//                    idadePassageiro = idadePassageiroState,
//                    tipoPagamento = mSelectedText,
//                    tipoTransporte = nSelectedText,
//                    escola = escolaSelectedText,
//                    usuario = usuario,
//                    motorista = motorista,
//                    context = context
//                )

                val intentSelect = Intent(context, ContratoActivity::class.java)

                intentSelect.putExtra("nome_responsavel", nomeResponsavelState)
                intentSelect.putExtra("nome_passageiro", nomePassageiroState)
                intentSelect.putExtra("idade_passageiro", idadePassageiroState)
                intentSelect.putExtra("tipo_pagamento", mSelectedText)
                intentSelect.putExtra("escola", nSelectedText)
                intentSelect.putExtra("tipo_transporte", nSelectedText)

                context.startActivity(intentSelect)

                Log.i("ds3m", "EnviarContrato: $nSelectedText")
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }


    }
}




