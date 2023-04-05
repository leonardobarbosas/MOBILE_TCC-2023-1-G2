package br.senai.sp.jandira.vanbora.components.forms.maincontainer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.DriverList
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


    val driversCall = GetFunctionsCall.getDriverCall().getAllDrivers()

    var drivers by remember {
        mutableStateOf(DriverList(listOf()))
    }

    driversCall.enqueue(object : Callback<Driver>{
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            Log.i("ds3m", "onResponse: ${response.body()!!}")
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
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
                items(drivers.drivers) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(6.dp),
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    ) {
                        Column() {
                            Image(
                                painter = rememberAsyncImagePainter(it.foto),
                                contentDescription = null,
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
                                            painter = rememberAsyncImagePainter(it.foto),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .height(50.dp)
                                                .width(50.dp)
                                                .clip(CircleShape)
                                                .border(2.dp, Color.Gray, CircleShape)
                                        )

                                        Spacer(modifier = Modifier.padding(2.dp))

                                        Column() {
                                            Text(text = it.nome, color = Color.Black)
                                            Spacer(modifier = Modifier.padding(2.dp))
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
                                                    imageVector = Icons.Filled.Star,
                                                    contentDescription = "",
                                                    tint = Color(238, 179, 31, 255)
                                                )
                                            }
                                        }
                                    }

                                    Column(
                                        modifier = Modifier.padding(end = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "16")
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