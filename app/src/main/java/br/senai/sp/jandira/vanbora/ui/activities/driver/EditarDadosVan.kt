package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.Rotas.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.Modelo
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.ui.activities.client.EditarPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.Motorista
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.KeyException

class EditarDadosVan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EditarVan()
                }
            }
        }
    }
}


@Preview
@Composable
fun EditarVan() {

    var placaState by rememberSaveable() {
        mutableStateOf("")
    }

    var isPlacaError by remember {
        mutableStateOf(false)
    }

    var modeloVanState by rememberSaveable() {
        mutableStateOf("")
    }

    var isModeloError by remember {
        mutableStateOf(false)
    }

    var numeroVagasState by rememberSaveable() {
        mutableStateOf("")
    }

    var isNumeroVagasError by remember {
        mutableStateOf(false)
    }

    var code by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }


    val context = LocalContext.current

    val intent = (context as EditarDadosVan).intent

    val idDriver = intent.getStringExtra("id")

    val idVan = intent.getStringExtra("id")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

    val vanCall = GetFunctionsCall.getVanCall().getVanById(id = idVan.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }

    var van by remember {
        mutableStateOf<Van?>(null)
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }

    })

    vanCall.enqueue(object : Callback<Van> {
        override fun onResponse(call: Call<Van>, response: Response<Van>) {
            van = response.body()!!
        }

        override fun onFailure(call: Call<Van>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }

    })



    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Placa
        OutlinedTextField(
            value = placaState,
            onValueChange = {
                placaState = it

                if (it == "" || it == null) {
                    isPlacaError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.placa_van)
                )
            },
            placeholder = {
                van?.let {
                    Text(
                        text = it.placa,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black
                        )
                    )
                }
            },
            trailingIcon = {
                if (isPlacaError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isPlacaError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isPlacaError) {
            Text(
                text = stringResource(id = R.string.isplaca_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        //Modelo da Van
        OutlinedTextField(
            value = modeloVanState,
            onValueChange = {
                modeloVanState = it

                if (it == "" || it == null) {
                    isModeloError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.modelo_van)
                )
            },
            placeholder = {
                van?.modelo?.get(0).let {
                    if (it != null) {
                        Text(
                            text = it.modelo,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Red
                            )
                        )
                    }
                }
            },
            trailingIcon = {
                if (isModeloError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isModeloError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isModeloError) {
            Text(
                text = stringResource(id = R.string.ismodelo_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        //Numero de Vagas
        OutlinedTextField(
            value = numeroVagasState,
            onValueChange = {
                numeroVagasState = it

                if (it == "" || it == null) {
                    isNumeroVagasError
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.numero_vagas)
                )
            },
            placeholder = {
                van?.let {
                    Text(
                        text = it.quantidade_vagas.toString(),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black
                        )
                    )
                }
            },
            trailingIcon = {
                if (isNumeroVagasError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isNumeroVagasError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isNumeroVagasError) {
            Text(
                text = stringResource(id = R.string.isnumero_vans_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.padding(26.dp))

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            Button(
//                onClick = {
//                    var van = Van(
//                        placaState,
//                        modeloVanState,
//                        van!!.id,
//                        van.foto,
//                        numeroVagasState,
//                        status_van = 1
//                    )
//
//                    var vanPutCall = GetFunctionsCall.getVanCall().putVan(van.id.toString(), van)
//
//                    vanPutCall.enqueue(object : Callback<String> {
//                        override fun onResponse(call: Call<String>, response: Response<String>) {
//                            code = response.code().toString()
//                            message = response.body().toString()
//                        }
//
//                        override fun onFailure(call: Call<String>, t: Throwable) {
//                            Log.i("ds3m", "onFailure: $t")
//                        }
//                    })
//
//                    if (code == "201") {
//                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "Van atualizada com sucesso",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//
//                        if (van != null) {
//                            val intentSelect = Intent(context, MotoristasActivity()::class.java)
//                            intentSelect.putExtra("id", van.id.toString())
//                        }
//                    }
//                },
//                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
//            ) {
//                Text(
//                    text = stringResource(id = R.string.save)
//                )
//            }
//
//            Button(
//                onClick = {
//                    var van = Van(
//                        placaState,
//                        modeloVanState,
//                        numeroVagasState,
//                        van!!.id,
//                        van!!.foto,
//                        status_van = 1
//                    )
//
//                    val callVanDelete = GetFunctionsCall.getVanCall().deleteVan(van.id)
//                    callVanDelete.enqueue(object : Callback<String> {
//                        override fun onResponse(call: Call<String>, response: Response<String>) {
//                            Toast.makeText(context, "Van deletada com sucesso", Toast.LENGTH_SHORT)
//                                .show()
//                            val intentSelect = Intent(context, SuaVan()::class.java)
//                            context.startActivity(intentSelect)
//                        }
//
//                        override fun onFailure(call: Call<String>, t: Throwable) {
//                            Log.i("ds3m", "onFailure: $t")
//                        }
//
//                    })
//                },
//                colors = ButtonDefaults.buttonColors(Color(250,210,69,255))
//            ) {
//                Text(
//                    text = stringResource(id = R.string.delete)
//                )
//            }
//
//        }
    }
}