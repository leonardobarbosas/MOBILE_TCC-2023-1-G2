package br.senai.sp.jandira.vanbora.components.forms.maincontainer

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.Header
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristaPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("RememberReturnType")
@Composable
fun MotoristasMain(
    viewModel: MainViewModel,
) {

    var context = LocalContext.current


    var filterState by remember {
        mutableStateOf("")
    }

    var driverByName by remember {
        mutableStateOf<DriverList?>(null)
    }

    var expanded by remember {
        mutableStateOf(false)
    }


    val intent = (context as MotoristasActivity).intent
    val idUser = intent.getStringExtra("id")
    val userCall = GetFunctionsCall.getUserCall().getUserById(id = idUser.toString())
    var user by remember {
        mutableStateOf<User?>(null)
    }
    userCall.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            user = response.body()!!
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure $t")
        }
    })


    val driversCall = GetFunctionsCall.getDriverCall().getAllDrivers()

    var drivers by remember {
        mutableStateOf(DriverList(listOf()))
    }

    var statusRequisition by remember {
        mutableStateOf(0)
    }

    if (statusRequisition == 1) {
        Log.i("ds3m", "onResponse: $statusRequisition")
        drivers = driverByName!!

        Log.i("ds3m", "onFailure: $drivers")
    } else if (statusRequisition == 0) {
        driversCall.enqueue(object : Callback<DriverList> {
            override fun onResponse(call: Call<DriverList>, response: Response<DriverList>) {
                drivers = response.body()!!
            }

            override fun onFailure(call: Call<DriverList>, t: Throwable) {
            }
        })
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header()

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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.TopEnd)
                ) {
                    IconButton(
                        onClick = { expanded = !expanded }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "",
                            modifier = Modifier
                                .height(46.dp)
                                .width(46.dp),
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                if (filterState != null && filterState != "") {

                                    val filterByName =
                                        GetFunctionsCall.getDriverCall().filterByName(filterState)

                                    filterByName.enqueue(object : Callback<DriverList> {
                                        override fun onResponse(
                                            call: Call<DriverList>,
                                            response: Response<DriverList>,
                                        ) {
                                            driverByName = response.body()!!

                                            statusRequisition = 1
                                        }

                                        override fun onFailure(
                                            call: Call<DriverList>,
                                            t: Throwable,
                                        ) {
                                            Log.i("ds3m", "onFailure: $t")
                                        }
                                    })

                                }
                            }
                        ) {
                            Text(text = "Nome")
                        }
                        DropdownMenuItem(
                            onClick = {
                                if (filterState != null && filterState != "") {

                                    val filterByName =
                                        GetFunctionsCall.getDriverCall().filterBySchool(filterState)

                                    filterByName.enqueue(object : Callback<DriverList> {
                                        override fun onResponse(
                                            call: Call<DriverList>,
                                            response: Response<DriverList>,
                                        ) {
                                            driverByName = response.body()!!

                                            statusRequisition = 1
                                        }

                                        override fun onFailure(
                                            call: Call<DriverList>,
                                            t: Throwable,
                                        ) {
                                            Log.i("ds3m", "onFailure: $t")
                                        }
                                    })

                                }
                            }
                        ) {
                            Text(text = "Escola")
                        }
                        DropdownMenuItem(
                            onClick = {
                                if (filterState != null && filterState != "") {

                                    val filterByName =
                                        GetFunctionsCall.getDriverCall().filterByPrice(filterState)

                                    filterByName.enqueue(object : Callback<DriverList> {
                                        override fun onResponse(
                                            call: Call<DriverList>,
                                            response: Response<DriverList>,
                                        ) {
                                            driverByName = response.body()!!

                                            statusRequisition = 1
                                        }

                                        override fun onFailure(
                                            call: Call<DriverList>,
                                            t: Throwable,
                                        ) {
                                            Log.i("ds3m", "onFailure: $t")
                                        }
                                    })

                                }
                            }
                        ) {
                            Text(text = "Preço")
                        }
                    }
                }


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
                                val intentSelect =
                                    Intent(context, MotoristaPerfilActivity::class.java)

                                intentSelect.putExtra("id_motorista", driver.id.toString())
                                intentSelect.putExtra("id_usuario", idUser)

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
                                painter = rememberAsyncImagePainter(driver.van?.get(0)?.foto),
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
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row() {
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

                                            if (driver?.avaliacao!! == 5.0) {
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
                                            } else if (driver?.avaliacao!! == 4.5) {
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
                                            } else if (driver?.avaliacao!! == 4.0) {
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
                                            } else if (driver?.avaliacao!! == 3.5) {
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
                                            } else if (driver?.avaliacao!! == 3.0) {
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
                                            } else if (driver?.avaliacao!! == 2.5) {
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
                                            } else if (driver?.avaliacao!! == 2.0) {
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
                                            } else if (driver?.avaliacao!! == 1.5) {
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
                                            } else if (driver?.avaliacao!! == 1.0) {
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


                                    Column(
                                        modifier = Modifier.padding(end = 16.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "${driver.van!![0].quantidade_vagas}")
                                        if (driver.van[0].quantidade_vagas == 1) {
                                            Text(
                                                text = "Vaga",
                                                fontSize = 12.sp,
                                                textAlign = TextAlign.Center
                                            )
                                        } else {
                                            Text(
                                                text = "Vagas",
                                                fontSize = 12.sp,
                                                textAlign = TextAlign.Center
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
}

