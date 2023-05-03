package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.api.retrofit.footer.FooterShow
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationPageActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NotificationPage()
                }
            }
        }
    }
    
    @Preview
    @Composable
    fun NotificationPage (){

        val context = LocalContext.current

        val intent = (context as NotificationPageActivity).intent

        val idDriver = intent.getStringExtra("id")

        val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

        var drivers by remember {
            mutableStateOf<Driver?>(null)
        }

        driverCall.enqueue(object : Callback<Driver> {
            override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
                drivers = response.body()!!
            }

            override fun onFailure(call: Call<Driver>, t: Throwable) {
                Log.i("ds3m", "onFailure: $t")
            }

        })

        Column (
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background2)
                )
                ) {
            
            FooterShow()

        }
    }
}