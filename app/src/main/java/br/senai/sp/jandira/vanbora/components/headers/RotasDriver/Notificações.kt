package br.senai.sp.jandira.vanbora.components.headers.RotasDriver

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.confirm.CustomDialog
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderMotorista
import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractPost
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristaPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.VisualizarContratosActivity
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Notificacoes(
    viewModel: MainViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            )
    ) {

        var code by remember {
            mutableStateOf("")
        }

        var message by remember {
            mutableStateOf("")
        }

        val context = LocalContext.current

        val intent = (context as SuasVansActivity).intent

        val idContract = intent.getStringExtra("id")
        Log.i("ds3m", "idContrato: $idContract")

        val contractCall =
            GetFunctionsCall.getContractCall().getContractDriverId(id = idContract.toString())

        var contracts by remember {
            mutableStateOf<Contract?>(null)
        }

        contractCall.enqueue(object : Callback<Contract> {
            override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
                contracts = response.body()!!
                Log.i("ds3m", "onResponse: $contracts")
            }

            override fun onFailure(call: Call<Contract>, t: Throwable) {
                Log.i("ds3m", "onFailure contract - meus contratos: $t")
            }
        })



        HeaderMotorista()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Notificações",
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    shadow = Shadow(
                        color = androidx.compose.ui.graphics.Color.Black,
                        offset = Offset(0F, 4F),
                        blurRadius = 5f
                    )
                )
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            contracts?.let {
                items(it.contracts) { contract ->

                    if (contract.status_contrato == 0) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                                .clickable {
                                    val intentSelect =
                                        Intent(context, MotoristaPerfilActivity::class.java)

                                    intentSelect.putExtra(
                                        "id_motorista",
                                        contract.motorista.id.toString()
                                    )

                                    context.startActivity(intentSelect)
                                },
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomEnd = 8.dp,
                                bottomStart = 8.dp
                            )
                        ) {


                            Column {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        contract.motorista.van?.get(
                                            0
                                        )?.foto
                                    ),
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                )

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    backgroundColor = Color(247, 233, 194, 255)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.SpaceAround,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row() {
                                            Image(
                                                painter = rememberAsyncImagePainter(contract.usuario.foto),
                                                contentDescription = "",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .height(50.dp)
                                                    .width(50.dp)
                                                    .clip(CircleShape)
                                                    .border(2.dp, Color.Gray, CircleShape)
                                            )

                                            Spacer(modifier = Modifier.padding(2.dp))

                                            Column(
                                                verticalArrangement = Arrangement.SpaceAround
                                            ) {
                                                Text(
                                                    text = contract.usuario.nome,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    text = contract.nome_passageiro,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    text = contract.tipo_contrato.tipo_contrato,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    text = contract.escola.nome,
                                                    color = Color.Black
                                                )

                                            }
                                        }


                                        Column(
                                            modifier = Modifier.padding(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Button(
                                                    onClick = {

                                                        val contracts = ContractPost(
                                                            contract.escola.id,
                                                            contract.motorista.id,
                                                            contract.tipo_contrato.id,
                                                            contract.tipo_pagamento.id,
                                                            contract.usuario.id,
                                                            status_contrato = 1,
                                                            contract.idade_passageiro.toString(),
                                                            contract.nome_passageiro,
                                                        )

                                                        val contractPutCall = GetFunctionsCall.getContractCall().putContract(contract.id.toString(), contracts)

                                                        contractPutCall.enqueue(object: Callback<String>{
                                                            override fun onResponse(
                                                                call: Call<String>,
                                                                response: Response<String>
                                                            ) {
                                                                code = response.code().toString()
                                                                message = response.body().toString()
                                                            }

                                                            override fun onFailure(
                                                                call: Call<String>,
                                                                t: Throwable
                                                            ) {
                                                                Log.i("ds3m", "onFailure: $t")
                                                            }

                                                        })

                                                        if (code == "201") {
                                                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                                        } else {
                                                            Toast.makeText(
                                                                context,
                                                                "Contrato aceito",
                                                                Toast.LENGTH_SHORT
                                                            )
                                                                .show()

                                                            if (contract != null) {
                                                                simulateHotReload(SuasVansActivity::class.java)
                                                            }
                                                        }
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color(5, 202, 9, 255)
                                                    )
                                                ) {
                                                    Text(text = "Aceitar Contrato")
                                                }

                                                Spacer(modifier = Modifier.padding(6.dp))

                                                Button(
                                                    onClick = {
                                                        viewModel.onPurchaseClick()
                                                    },
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Color.Red
                                                    )
                                                ) {
                                                    Text(text = "Cancelar contrato")
                                                }
                                                if (viewModel.isDialogShown) {
                                                    CustomDialog(
                                                        onDismiss = {
                                                            viewModel.onDismissDialog()
                                                        },
                                                        onConfirm = {
                                                            val contract = ContractX(
                                                                contracts!!.contracts[0].escola,
                                                                contracts!!.contracts[0].id,
                                                                contracts!!.contracts[0].status_contrato,
                                                                contracts!!.contracts[0].idade_passageiro,
                                                                contracts!!.contracts[0].motorista,
                                                                contracts!!.contracts[0].nome_passageiro,
                                                                contracts!!.contracts[0].tipo_contrato,
                                                                contracts!!.contracts[0].tipo_pagamento,
                                                                contracts!!.contracts[0].usuario,
                                                            )

                                                            val callContractDelete =
                                                                GetFunctionsCall.getContractCall()
                                                                    .deleteContract(contract.id)
                                                            callContractDelete.enqueue(object :
                                                                Callback<String> {
                                                                override fun onResponse(
                                                                    call: Call<String>,
                                                                    response: Response<String>
                                                                ) {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Contrato encerrado com sucesso",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                    simulateHotReload(context)

                                                                }

                                                                override fun onFailure(
                                                                    call: Call<String>,
                                                                    t: Throwable
                                                                ) {
                                                                    Log.i("ds3m", "fali")
                                                                }

                                                            })

                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            }
        }

    }
}