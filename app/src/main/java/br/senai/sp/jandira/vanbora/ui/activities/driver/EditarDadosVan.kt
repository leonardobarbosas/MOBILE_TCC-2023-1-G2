package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.Rotas.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.ui.activities.client.EditarPerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarDadosVan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
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

    val context = LocalContext.current

    val intent = (context as EditarDadosVan).intent

    val idDriver = intent.getStringExtra("id")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
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
        OutlinedTextField(
            value = placaState,
            onValueChange = {
                placaState = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(text = stringResource(id = R.string.placa_van))
            },
            placeholder = {
                
            }
        )
    }
}