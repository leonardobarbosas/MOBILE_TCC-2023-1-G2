package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationPageActivity : ComponentActivity() {

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
    fun NotificationPage() {

        var filterState by remember {
            mutableStateOf("")
        }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background2)
                )
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
                }
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
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    }
}
