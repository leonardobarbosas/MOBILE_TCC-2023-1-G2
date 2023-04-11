package br.senai.sp.jandira.vanbora.components.forms.maincontainer

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.ui.activities.client.PerfilActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MotoristasMain() {

    var filterState by remember {
        mutableStateOf("")
    }

    var expandState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val driversCall = GetFunctionsCall.getDriverCall().getAllDrivers()

    var drivers by remember {
        mutableStateOf(DriverList(listOf()))
    }

    driversCall.enqueue(object : Callback<DriverList> {
        override fun onResponse(call: Call<DriverList>, response: Response<DriverList>) {
            drivers = response.body()!!
        }

        override fun onFailure(call: Call<DriverList>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = filterState,
                    onValueChange = {
                        filterState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = Color(238, 179, 31, 255)
                        )
                    },
                    shape = RoundedCornerShape(40.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(208, 173, 82, 255),
                        unfocusedBorderColor = Color(208, 173, 82, 255),
                        backgroundColor = Color(255, 248, 228, 255)
                    )
                )

                Spacer(modifier = Modifier.padding(6.dp))

                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "",
                    modifier = Modifier
                        .height(46.dp)
                        .width(46.dp),
                    tint = Color.Black
                )

            }

            Spacer(modifier = Modifier.padding(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(drivers.drivers) { driver ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(6.dp)
                            .clickable {
                                expandState = true

                                val intentSelect = Intent(context, PerfilActivity::class.java)

                                intentSelect.putExtra("id", driver.id.toString())

                                context.startActivity(intentSelect)
                            },
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    ) {
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(driver.van[0].foto),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(1f),
                                backgroundColor = Color(247, 233, 194, 255)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(driver.foto),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .height(50.dp)
                                                .width(50.dp)
                                                .clip(CircleShape)
                                                .border(2.dp, Color.Gray, CircleShape)
                                        )

                                        Spacer(modifier = Modifier.padding(2.dp))

                                        Column {
                                            Text(text = driver.nome, color = Color.Black)

                                            Spacer(modifier = Modifier.padding(2.dp))

                                            Log.i("ds3m", "Avaliacao: ${driver.avaliacao}")

                                                if (driver.avaliacao == 10) {
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
                                                }else if (driver.avaliacao == 9){
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
                                                            imageVector = Icons.Filled.StarHalf,
                                                            contentDescription = "",
                                                            tint = Color(238, 179, 31, 255)
                                                        )
                                                    }
                                                }else if (driver.avaliacao == 8){
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
                                                            imageVector = Icons.Filled.StarBorder,
                                                            contentDescription = "",
                                                            tint = Color(238, 179, 31, 255)
                                                        )
                                                    }
                                                }else if (driver.avaliacao == 7){
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
                                                }else if (driver.avaliacao == 6){
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
                                                }else if (driver.avaliacao == 5){
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
                                                }else if (driver.avaliacao == 4){
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
                                                }else if (driver.avaliacao == 3){
                                                    Row {
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
                                                }else if (driver.avaliacao == 2){
                                                    Row {
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
                                                }else{
                                                    Row {
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

                                    Column(
                                        modifier = Modifier.padding(end = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "${driver.van[0].quantidade_vagas}")
                                        Text(text = "Vagas")
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