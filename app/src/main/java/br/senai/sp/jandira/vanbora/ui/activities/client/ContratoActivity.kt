package br.senai.sp.jandira.vanbora.ui.activities.client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.confirm.Dialog
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.HeaderPerfil
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewContract
import br.senai.sp.jandira.vanbora.model.contract.Escola
import br.senai.sp.jandira.vanbora.model.contract.TipoContrato
import br.senai.sp.jandira.vanbora.model.contract.TipoPagamento
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContratoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Contrato(
                        viewModel = MainViewModel()
                    )
                }
            }
        }
    }
}

@Composable
fun Contrato(
    viewModel: MainViewModel
) {

    var context = LocalContext.current

    val intent = (context as ContratoActivity).intent

    var usuario by remember {
        mutableStateOf<User?>(null)
    }
    val idUser = intent.getStringExtra("id_usuario")
    Log.i("ds3m", "idUser: $idUser")
    val userCall = GetFunctionsCall.getUserCall().getUserById(id = idUser.toString())
    userCall.enqueue(object: Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            usuario = response.body()!!
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure $t")
        }
    })


    var driver by remember {
        mutableStateOf<Driver?>(null)
    }
    val idDriver = intent.getStringExtra("id_motorista")
    Log.i("ds3m", "idDriver: $idDriver")
    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())
    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure driver")
        }
    })

    val idTipoPagamento = intent.getStringExtra("id_tipo_pagamento")
    val idTipoContrato = intent.getStringExtra("id_tipo_contrato")
    val idEscola = intent.getStringExtra("id_escola")
    val escola = intent.getStringExtra("tipo_escola")
    val tipoContrato = intent.getStringExtra("tipo_contrato")
    val tipoPagamento = intent.getStringExtra("tipo_pagamento")

    val nomeResponsavel = intent.getStringExtra("nome_responsavel")
    val nomePassageiro = intent.getStringExtra("nome_passageiro")
    val idadePassageiro = intent.getStringExtra("idade_passageiro")



    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(255, 248, 228, 255)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Verificar informações de contrato", fontSize = 22.sp)

                    Text(text = "Nome do Responsável: $nomeResponsavel")
                    Text(text = "Nome do Passageiro: $nomePassageiro")
                    Text(text = "Idade do Passageiro: $idadePassageiro")
                    Text(text = "Tipo de Pagamento: $tipoPagamento")
                    Text(text = "Tipo de Contrato: $tipoContrato")
                    Text(text = "Escola: $escola")

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = {

                                    viewModel.onPurchaseClick()

                                },
                                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
                            ) {
                                Text(text = stringResource(R.string.send_contract))
                            }
                            if(viewModel.isDialogShown){
                                Dialog(
                                    onDismiss = {
                                        viewModel.onDismissDialog()
                                    },
                                    onConfirm = {
                                        Log.i("ds3m", "Contrato: teste")
//                                        RegisterNewContract(
//                                        nomePassageiro = nomePassageiro.toString(),
//                                        idadePassageiro = idadePassageiro.toString(),
//                                        tipoPagamento = TipoPagamento(
//                                            id = idTipoPagamento!!.toInt(),
//                                            tipo_pagamento = tipoPagamento.toString(),
//                                            status_tipo_pagamento = 1
//                                        ),
//                                        tipoTransporte = TipoContrato(
//                                            id = idTipoContrato!!.toInt(),
//                                            tipo_contrato = tipoContrato.toString(),
//                                            status_tipo_contrato = 1
//                                        ),
//                                        escola = Escola(
//                                            id = idEscola!!.toInt(),
//                                            nome = escola.toString(),
//                                            status_escola = 1
//                                        ),
//                                        usuario = User(
//                                            cep = usuario!!.cep,
//                                            cpf = usuario!!.cpf,
//                                            data_nascimento = usuario!!.data_nascimento,
//                                            email = usuario!!.email,
//                                            foto = usuario!!.foto,
//                                            id = idUser!!.toInt(),
//                                            nome = usuario!!.nome,
//                                            rg = usuario!!.rg,
//                                            senha = usuario!!.senha,
//                                            telefone = usuario!!.telefone,
//                                            status_usuario = usuario!!.status_usuario,
//                                        ),
//                                        motorista = Driver(
//                                            avaliacao = driver!!.avaliacao,
//                                            cnh = driver!!.cnh,
//                                            cpf = driver!!.cpf,
//                                            data_nascimento = driver!!.data_nascimento,
//                                            descricao = driver!!.descricao,
//                                            email = driver!!.email,
//                                            foto = driver!!.foto,
//                                            id = idDriver!!.toInt(),
//                                            inicio_carreira = driver!!.inicio_carreira,
//                                            nome = driver!!.nome,
//                                            rg = driver!!.rg,
//                                            senha = driver!!.senha,
//                                            status_motorista = driver!!.status_motorista,
//                                            telefone = driver!!.telefone,
//                                            van = driver!!.van
//                                        ),
//                                        context = context
//                                    )
                                        

                                        Toast.makeText(context, "Contrato criado com sucesso!", Toast.LENGTH_SHORT).show()

                                        val intentSelect = Intent(context, MotoristasActivity::class.java)

                                        intentSelect.putExtra("id", idUser)

                                        context.startActivity(intentSelect)

                                    }
                                )
                            }



                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
                            ) {
                                Text(text = stringResource(R.string.download_contract))
                            }
                        }

                    }
                }
            }


        }
    }

}
