package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.teste.comentarios
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val systemUi = rememberSystemUiController()
                    SideEffect {
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0), darkIcons = true
                        )
                    }
                    Perfil()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Perfil() {

    val context = LocalContext.current

    val localizeMain by remember {
        mutableStateOf(LocalizeActivity::class.java)
    }

    val intent = (context as PerfilActivity).intent

    val idUser = intent.getStringExtra("id")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idUser.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }

    driverCall.enqueue(object : Callback<Driver>{
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            Log.i("ds3m", "onResponse: ${response.body()!!}")
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })

    Column {
        //Header
        HeaderSelectDriverComplement(
            context = context, componentActivity = localizeMain.newInstance()
        )

        //Main
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(driver?.foto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                )

                Button(onClick = {

                    Log.i("ds3m", "Perfil: $idUser")
                }) {
                    Text(text = "add")
                }
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(255, 237, 185, 255))
                        .fillMaxWidth()
                ) {

                    Text(text = "2 comments", fontSize = 26.sp)

                    comentarios()


                }
            }
        }
    }
}



