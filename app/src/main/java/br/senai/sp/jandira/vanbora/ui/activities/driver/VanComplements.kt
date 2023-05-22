package br.senai.sp.jandira.vanbora.ui.activities.driver

import VanInfos
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.headers.HeaderComplements
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory

class VanComplements : ComponentActivity() {
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
                        systemUi.setStatusBarColor(color = Color(255, 255, 255, 0), darkIcons = true)
                    }
                    VanInfosActivity()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VanInfosActivity(){

    val context = LocalContext.current

    val intent = (context as VanComplements).intent

    val name = intent.getStringExtra("driver")

    val driver =Gson().fromJson(name.toString(), DriverPost::class.java)

    Log.i("ds3m", "VanInfosActivity: $driver")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),

    ) {

        //Header
        Column() {
            HeaderComplements()
        }
        //DIV PAI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VanInfos(driver = driver)

        }
    }
}
