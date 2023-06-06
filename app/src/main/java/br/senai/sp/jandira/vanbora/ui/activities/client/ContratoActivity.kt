package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
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
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewContract
import br.senai.sp.jandira.vanbora.model.contract.ContractPost
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.ui.theme.VanboraTheme
import com.google.gson.Gson
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

    val contract = Gson().fromJson(intent.getStringExtra("contract"), ContractPost::class.java)

    var usuario by remember {
        mutableStateOf<User?>(null)
    }
    val idUser = intent.getStringExtra("id_user").toString()
    val userCall = GetFunctionsCall.getUserCall().getUserById(id = idUser)
    userCall.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful){
                usuario = response.body()!!
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure  user: ${t.message}")
        }
    })


    var driver by remember {
        mutableStateOf<Driver?>(null)
    }
    val idDriver = intent.getStringExtra("id_driver").toString()
    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver)
    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
            if (response.isSuccessful){
                driver = response.body()!!
            }
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure driver ${t.message}")
        }
    })



    val escola = intent.getStringExtra("tipo_escola").toString()
    val tipoContrato = intent.getStringExtra("tipo_contrato").toString()
    val tipoPagamento = intent.getStringExtra("tipo_pagamento").toString()


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

                    Text(text = "Nome do Responsável: $idUser")
                    Text(text = "Nome do Passageiro: ${contract.nome_passageiro}")
                    Text(text = "Idade do Passageiro: ${contract.idade_passageiro}")
                    Text(text = "Tipo de Pagamento: $tipoPagamento")
                    Text(text = "Tipo de Contrato: $tipoContrato")
                    Text(text = "Escola: $escola")
                    Text(text = "Preço: ${driver?.id_preco?.faixa_preco.toString()}")

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {

                                    viewModel.onPurchaseClick()

                                },
                                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
                            ) {
                                Text(text = stringResource(R.string.send_contract))
                            }
                            if (viewModel.isDialogShown) {
                                Dialog(
                                    onDismiss = {
                                        viewModel.onDismissDialog()
                                    },
                                    onConfirm = {


                                        RegisterNewContract(
                                            contract = contract,
                                            context = context
                                        )

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
