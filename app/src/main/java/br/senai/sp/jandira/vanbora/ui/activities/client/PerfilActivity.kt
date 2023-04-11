package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
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
import java.util.Calendar

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

@Composable
fun Perfil() {

    val scrollState = rememberScrollState()

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


    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
            Log.i("ds3m", "onResponse: ${response.body()!!}")
            Log.i("ds3m", "onResponse: ${response.body()!!.nome}")
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
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(252, 241, 211, 255))
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .background(Color.Black, shape = RoundedCornerShape(25.dp))
                    .height(200.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = rememberAsyncImagePainter(driver?.van?.get(0)?.foto),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(30.dp))
                        .background(Color.Black, shape = RoundedCornerShape(25.dp))
                        .border(BorderStroke(2.dp, SolidColor(Color.Black)))
                )
                Image(
                    painter = rememberAsyncImagePainter(driver?.foto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
            }

            Spacer(modifier = Modifier.padding(6.dp))

            Text(
                text = "${driver?.nome}",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Avaliacao()

            Spacer(modifier = Modifier.padding(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${driver?.van?.get(0)?.quantidade_vagas}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Vagas",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${driver?.inicio_carreira}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Anos de",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "transporte",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${driver?.descricao}", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.padding(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Whatsapp,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(5, 172, 27, 255)
                    )
                    Text(text = "${driver?.telefone}", fontSize = 14.sp, textAlign = TextAlign.Center)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(0, 133, 255, 255)
                    )
                    Text(text = "${driver?.telefone}", fontSize = 14.sp, textAlign = TextAlign.Center)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(0, 0, 0, 255)
                    )
                    Text(text = "${driver?.email}", fontSize = 14.sp, textAlign = TextAlign.Center)
                }
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
            ) {
                Text(
                    text = stringResource(id = R.string.comments)
                )
            }





//            Card(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .background(Color(255, 237, 185, 255))
//                        .fillMaxWidth()
//                ) {
//
//                    Text(text = "2 comments", fontSize = 26.sp)
//
//                    comentarios()

//                }
//            }
        }
    }
}

@Composable
fun Avaliacao() {

    val context = LocalContext.current

    val intent = (context as PerfilActivity).intent

    val idUser = intent.getStringExtra("id")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idUser.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }
    var testeDriver by remember {
        mutableStateOf(0)
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {


            if (response.body()!! != null) {
                testeDriver = 1

                driver = response.body()!!
            }
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })

    if (testeDriver == 1) {
        if (driver?.avaliacao!! == 10) {
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 9) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 8) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 7) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 6) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 5) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 4) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 3) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 2) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else {
            Row() {
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        }
    }
}



