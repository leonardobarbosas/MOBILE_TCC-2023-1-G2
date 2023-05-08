package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.components.headers.HeaderVisualizar
import br.senai.sp.jandira.vanbora.components.headers.Rotas.MeusContratos
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.ui.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisualizarContratosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VisualizarContratos()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VisualizarContratos() {

    val context = LocalContext.current

    val intent = (context as VisualizarContratosActivity).intent

    val idContract = intent.getStringExtra("id").toString()


    val contractCall =
        GetFunctionsCall.getContractCall().getOneContractId(id = idContract)

    var contracts by remember {
        mutableStateOf<ContractX?>(null)
    }

    contractCall.enqueue(object : Callback<ContractX> {
        override fun onResponse(call: Call<ContractX>, response: Response<ContractX>) {
            contracts = response.body()!!
        }

        override fun onFailure(call: Call<ContractX>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })


    Column(modifier = Modifier.fillMaxSize()) {

        HeaderVisualizar()


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
                    Text(text = "Nome do Responsável: ${contracts?.usuario?.nome}")
                    Text(text = "Nome do Passageiro: ${contracts?.nome_passageiro}")
                    Text(text = "Idade do Passageiro: ${contracts?.idade_passageiro}")
                    Text(text = "Tipo de Pagamento: ${contracts?.tipo_pagamento?.tipo_pagamento}")
                    Text(text = "Tipo de Contrato: ${contracts?.tipo_contrato?.tipo_contrato}")
                    Text(text = "Escola: ${contracts?.escola?.nome}")

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
