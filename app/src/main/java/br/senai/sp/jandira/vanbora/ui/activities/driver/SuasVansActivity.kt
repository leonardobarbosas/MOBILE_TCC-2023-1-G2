package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.user.footer.FooterShow
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
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
                    SuaVan()
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
        Row(

        ) {
            Text(
                text = stringResource(id = R.string.suas_vans_suas),
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#E0B441"))
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(id = R.string.suas_vans_vans),
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
                )
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(drivers.drivers) { driver ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp)
                        .padding(6.dp)
                        .clickable {
                            val vanSelect = Intent(context, "---TELA DE INFO VAN---"::class.java)

                            vanSelect.putExtra("id", driver.van?.get(0)?.id)

                            context.startActivity(vanSelect)
                        },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                ) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(model = driver.van?.get(0)?.foto),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .fillMaxWidth(),
                            backgroundColor = Color(247, 233, 194, 255)
                        ) {
                            Row() {
                                Column {
                                    Text(text = "${driver.van?.get(0)?.modelo}")
                                    driver.van?.get(0)?.let { Text(text = it.placa)}
                                }
                                Column(
                                    modifier = Modifier.padding(end = 16.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(text = "${driver.van?.get(0)?.quantidade_vagas}")
                                }
                            }
                        }
                    }

                }
            }
        }

        FooterShow()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        SuaVan()
    }
}