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
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewContract
import br.senai.sp.jandira.vanbora.model.contract.*
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EnviarContrato() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current

        val intent = (context as EnviarContratoActivity).intent

        var usuario by remember {
            mutableStateOf<User?>(null)
        }
        val idUser = intent.getStringExtra("id_usuario")

        val userCall = GetFunctionsCall.getUserCall().getUserById(id = idUser.toString())
        userCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                usuario = response.body()!!
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("ds3m", "tttttttttttttttt $t")
            }
        })


        var driver by remember {
            mutableStateOf<Driver?>(null)
        }
        val idDriver = intent.getStringExtra("id_motorista")
        val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())
        driverCall.enqueue(object : Callback<Driver> {
            override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
                driver = response.body()!!
            }

            override fun onFailure(call: Call<Driver>, t: Throwable) {
                Log.i("ds3m", "onFailure driver")
            }
        })


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


        //Tipo de Transporte
        val tipoTransporteCall = GetFunctionsCall.getTipoTransporteCall().getAllContracts()
        var tipoTransportes by remember {
            mutableStateOf(TipoTransporteList(listOf()))
        }
        tipoTransporteCall.enqueue(object : Callback<TipoTransporteList> {
            override fun onResponse(
                call: Call<TipoTransporteList>,
                response: Response<TipoTransporteList>
            ) {
                tipoTransportes = response.body()!!
            }

            override fun onFailure(call: Call<TipoTransporteList>, t: Throwable) {
                Log.i("ds3m", "onFailure tipotransporte: ${t.message}")
            }
        })

        //Escola
        val escolaCall = GetFunctionsCall.getEscolaCall().getSchoolByDriver(id = idDriver.toString())
        var escolas by remember {
            mutableStateOf(EscolaDriver(listOf()))
        }
        escolaCall.enqueue(object : Callback<EscolaDriver> {
            override fun onResponse(call: Call<EscolaDriver>, response: Response<EscolaDriver>) {
                if (response.isSuccessful){
                    escolas = response.body()!!
                }
            }

            override fun onFailure(call: Call<EscolaDriver>, t: Throwable) {
                Log.i("ds3m", "onFailure escolas: ${t.message}")
            }
        })

        //Tipo de Pagamento
        val tipoPagamentoCall = GetFunctionsCall.getTipoPagamentoCall().getAllPayments()
        var tipoPagamentos by remember {
            mutableStateOf(TipoPagamentoList(listOf()))
        }
        tipoPagamentoCall.enqueue(object : Callback<TipoPagamentoList> {
            override fun onResponse(
                call: Call<TipoPagamentoList>,
                response: Response<TipoPagamentoList>
            ) {
                tipoPagamentos = response.body()!!
            }

            override fun onFailure(call: Call<TipoPagamentoList>, t: Throwable) {
                Log.i("ds3m", "onFailure tipo pagament: ${t.message}")
            }

        })


        //Tipo Transporte
        var mExpanded by remember { mutableStateOf(false) }
        var mSelectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown
        var idTipoContrato by remember {
            mutableStateOf(0)
        }


        //Tipo Pagamento
        var idTipoPagamento by remember {
            mutableStateOf(0)
        }
        var nExpanded by remember { mutableStateOf(false) }
        var nSelectedText by remember { mutableStateOf("") }
        var nTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val nIcon = if (nExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown


        //Escola - Teste
        var idEscola by remember {
            mutableStateOf(0)
        }
        var escolaState by remember { mutableStateOf(false) }
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
            readOnly = true,
            label = {
                Text(
                    text = usuario?.nome.toString(),
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

                if (it == "") {
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

                if (it == "") {
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
                readOnly = true,
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
                tipoTransportes.typesContracts.map {
                    DropdownMenuItem(onClick = {
                        idTipoContrato = it.id
                        mSelectedText = it.tipo_contrato
                        mExpanded = false
                    }) {
                        Text(text = it.tipo_contrato)
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
                readOnly = true,
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
                escolas.schools.map {
                    DropdownMenuItem(onClick = {
                        idEscola = it.id_escola
                        escolaSelectedText = it.nome_escola
                        escolaState = false
                    }) {
                        Text(text = it.nome_escola)
                    }
                }
            }
        }

        //Tipo de pagamento
        Column {
            OutlinedTextField(
                value = nSelectedText,
                onValueChange = { nSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        nTextFieldSize = coordinates.size.toSize()
                    }
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                readOnly = true,
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
                tipoPagamentos.typesPayment.map {
                    DropdownMenuItem(onClick = {
                        idTipoPagamento = it.id
                        nSelectedText = it.tipo_pagamento
                        nExpanded = false
                    }) {
                        Text(text = it.tipo_pagamento)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))





        Button(
            onClick = {

                var contract = ContractPost(
                    id_escola = idEscola,
                    id_motorista = idDriver.toString().toInt(),
                    id_tipo_contrato = idTipoContrato,
                    id_tipo_pagamento = idTipoPagamento,
                    id_usuario = idUser.toString().toInt(),
                    status_contrato = 0,
                    idade_passageiro = idadePassageiroState,
                    nome_passageiro = nomePassageiroState
                )

                val intentSelect = Intent(context, ContratoActivity::class.java)

                intentSelect.putExtra("contract", Gson().toJson(contract))
                intentSelect.putExtra("tipo_escola", escolaSelectedText)
                intentSelect.putExtra("tipo_contrato", mSelectedText)
                intentSelect.putExtra("tipo_pagamento", nSelectedText)
                intentSelect.putExtra("id_user", idUser)
                intentSelect.putExtra("id_driver", idDriver)

                context.startActivity(intentSelect)

            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }


    }
}




