package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuasVansActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var context = LocalContext.current

    var drivers by remember {
        mutableStateOf(DriverList(listOf()))
    }

    val driversCall = GetFunctionsCall.getDriverCall().getAllDrivers()


    driversCall.enqueue(object : Callback<DriverList> {
        override fun onResponse(call: Call<DriverList>, response: Response<DriverList>) {
            drivers = response.body()!!
        }

        override fun onFailure(call: Call<DriverList>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
            ) {
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        Greeting("Android")
    }
}