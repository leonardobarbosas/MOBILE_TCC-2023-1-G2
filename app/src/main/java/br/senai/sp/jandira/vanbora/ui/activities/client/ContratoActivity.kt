package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.HeaderPerfil
import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.ui.activities.client.ui.theme.VanboraTheme
import retrofit2.Callback

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
                    Contrato()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Contrato() {

    var context = LocalContext.current

    val intent = (context as ContratoActivity).intent

    val nomeResponsavel = intent.getStringExtra("nome_responsavel")
    val nomePassageiro = intent.getStringExtra("nome_passageiro")
    val idadePassageiro = intent.getStringExtra("idade_passageiro")
    val tipoPagamento = intent.getStringExtra("tipo_pagamento")
    val escola = intent.getStringExtra("escola")
    val tipoTransporte = intent.getStringExtra("tipo_transporte")



    Column(modifier = Modifier.fillMaxSize()) {

        HeaderPerfil()


        Column(
            modifier = Modifier
                .padding(48.dp),
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
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Nome do Responsável: $nomeResponsavel")
                    Text(text = "Nome do Passageiro: $nomePassageiro")
                    Text(text = "Idade do Passageiro: $idadePassageiro")
                    Text(text = "Tipo de Pagamento: $tipoPagamento")
                    Text(text = "Tipo de Contrato: $escola")
                    Text(text = "Escola: $tipoTransporte")

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
                            ) {
                                Text(text = stringResource(R.string.send_contract))
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
