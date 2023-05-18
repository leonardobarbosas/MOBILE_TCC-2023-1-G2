package br.senai.sp.jandira.vanbora.ui.activities.driver


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.driver.footer.FooterDriver
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderMotorista
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

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
                    val systemUi = rememberSystemUiController()
                    SideEffect {
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0),
                            darkIcons = true
                        )
                    }
                    HeaderMotorista()

                    SuaVan()

                    FooterDriver()
                }
            }
        }
    }
}

@Composable

fun SuaVan() {

    val context = LocalContext.current
    

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


    Column(
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background2),
                    contentScale = ContentScale.Crop
                )
        },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Vans()

    }

}