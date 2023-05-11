package br.senai.sp.jandira.vanbora.components.headers.Rotas

import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.confirm.CustomDialog
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.Header
import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristaPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.VisualizarContratosActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Notifications (
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

        val idPerfil = intent.getStringExtra("id_usuario")

        val perfilCall = GetFunctionsCall.getUserCall().getUserById(id = idPerfil.toString())

        var perfil by remember {
            mutableStateOf<User?>(null)
        }

        perfilCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                perfil = response.body()!!
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("ds3m", "onFailure user - notificacoes: $t")
            }
        })


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
                Log.i("ds3m", "onFailure contract - notificacoes: $t")
            }
        })

        Header()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                contracts?.let {
                    items(it.contracts) { contract ->

                        if (contract.status_contrato == 0){

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
                                                            intentSelect.putExtra(
                                                                "id_usuario",
                                                                idPerfil
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
                                                modifier = Modifier.padding(),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Button(
                                                        onClick = { /*TODO*/ },
                                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(251, 211, 69, 255)
                                                        )
                                                    ) {
                                                        Text(text = "Contrato em espera")
                                                    }
                                                    
                                                    Spacer(modifier = Modifier.padding(6.dp))
                                                    
                                                    Button(
                                                        onClick = {
                                                            viewModel.onPurchaseClick()
                                                        },
                                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                                    ) {
                                                        Text(text = "Cancelar contrato")
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
                                                                    contracts!!.contracts[0].status_contrato,
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

    }
}