package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderMotorista
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.Van
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun VanMain() {

    val context = LocalContext.current


    val intent = (context as SuasVansActivity).intent

    val idDriver = intent.getStringExtra("id")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

    var drivers by remember {
        mutableStateOf<Driver?>(null)
    }

    var vans by remember {
        mutableStateOf<List<Van>>(listOf())
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            drivers = response.body()!!
            vans = drivers!!.van!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderMotorista()


        Row(

        ) {
            Text(
                text = stringResource(id = R.string.suas_vans_suas),
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#E0B441")),
                    shadow = Shadow(
                        color = androidx.compose.ui.graphics.Color.Black,
                        offset = Offset(0F, 4F),
                        blurRadius = 5f
                    )
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(id = R.string.suas_vans_vans),
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    shadow = Shadow(
                        color = androidx.compose.ui.graphics.Color.Black,
                        offset = Offset(0F, 4F),
                        blurRadius = 5f
                    )
                )
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(vans) { van ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(250.dp)
                        .padding(6.dp)
                        .clickable {
                            val vanSelect = Intent(context, EditarDadosVan::class.java)
                            vanSelect.putExtra("id_motorista", idDriver)
                            vanSelect.putExtra("id_van", van.id.toString())
                            vanSelect.putExtra("url_img_van", van.foto)
                            context.startActivity(vanSelect)
                        },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 5.dp
                    )
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(model = van.foto),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .fillMaxWidth(),
                            backgroundColor = Color(247, 233, 194, 255)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 16.dp, end = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
//
                                    Text(
                                        text = van.modelo[0].modelo,
                                        fontSize = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                                    )
                                    Text(
                                        text = van.placa,
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                                    )
                                }

                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = van.quantidade_vagas.toString(),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (van.quantidade_vagas == 1) {
                                        Text(
                                            text = "Vaga",
                                            fontSize = 15.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_semibold))
                                        )
                                    } else {
                                        Text(
                                            text = stringResource(id = R.string.vagas_vans),
                                            fontSize = 15.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins_semibold))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

