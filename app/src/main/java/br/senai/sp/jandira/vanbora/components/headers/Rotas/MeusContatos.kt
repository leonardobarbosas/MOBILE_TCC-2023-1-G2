package br.senai.sp.jandira.vanbora.components.headers.Rotas

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.confirm.CustomDialog
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.Header
import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristaPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.VisualizarContratosActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MeusContratos(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val context = LocalContext.current

        val intent = (context as MotoristasActivity).intent

        val idContract = intent.getStringExtra("id")

        val contractCall = GetFunctionsCall.getContractCall().getContractId(id = idContract.toString())

        var contracts by remember {
            mutableStateOf<Contract?>(null)
        }

        contractCall.enqueue(object: Callback<Contract> {
            override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
                contracts = response.body()!!
                Log.i("ds3m", "onResponse: $contracts")
            }

            override fun onFailure(call: Call<Contract>, t: Throwable) {
                Log.i("ds3m", "onFailure: $t")
            }
        })

        Header()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Seus Contratos", fontSize = 32.sp)

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                contracts?.let {
                    items(it.contracts) { contract ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(6.dp)
                                .clickable {
                                    val intentSelect = Intent(context, MotoristaPerfilActivity::class.java)

                                    intentSelect.putExtra("id", contract.motorista.id.toString())

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
                                        .height(130.dp)
                                )

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(1f),
                                    backgroundColor = Color(247, 233, 194, 255)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(start = 16.dp)
                                        ) {
                                            Image(
                                                painter = rememberAsyncImagePainter(contract.motorista.foto),
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
                                                    text = contract.motorista.nome,
                                                    color = Color.Black
                                                )

                                                Button(
                                                    onClick = {
                                                        val intentSelect = Intent(
                                                            context,
                                                            VisualizarContratosActivity::class.java
                                                        )

                                                        intentSelect.putExtra(
                                                            "id",
                                                            contract.id.toString()
                                                        )

                                                        context.startActivity(intentSelect)

                                                        Log.i(
                                                            "ds3m", "MeusContratos: ${contract.id}"
                                                        )
                                                    },
                                                    modifier = Modifier.height(25.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        Color(
                                                            251,
                                                            211,
                                                            69,
                                                            255
                                                        )
                                                    )
                                                ) {
                                                    Text(text = "Infos. contrato", fontSize = 5.sp)
                                                }
                                            }
                                        }


                                        Column(
                                            modifier = Modifier.padding(end = 16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Button(
                                                onClick = {
                                                    viewModel.onPurchaseClick()
                                                },
                                                shape = CircleShape,
                                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                            ) {
                                                Image(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = ""
                                                )
                                            }
                                            if(viewModel.isDialogShown){
                                                CustomDialog(
                                                    onDismiss = {
                                                        viewModel.onDismissDialog()
                                                    },
                                                    onConfirm = {
                                                        val contract = ContractX(
                                                            contracts!!.contracts[0].escola,
                                                            contracts!!.contracts[0].id,
                                                            contracts!!.contracts[0].idade_passageiro,
                                                            contracts!!.contracts[0].motorista,
                                                            contracts!!.contracts[0].nome_passageiro,
                                                            contracts!!.contracts[0].tipo_contrato,
                                                            contracts!!.contracts[0].tipo_pagamento,
                                                            contracts!!.contracts[0].usuario,
                                                        )

                                                        val callContractDelete = GetFunctionsCall.getContractCall().deleteContract(contract.id)
                                                        callContractDelete.enqueue(object: Callback<String> {
                                                            override fun onResponse(
                                                                call: Call<String>, response: Response<String>
                                                            ) {
                                                                Toast.makeText(context, "Contrato encerrado com sucesso", Toast.LENGTH_SHORT).show()
                                                                simulateHotReload(context)

                                                            }
                                                            override fun onFailure(call: Call<String>, t: Throwable) {
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

//addd