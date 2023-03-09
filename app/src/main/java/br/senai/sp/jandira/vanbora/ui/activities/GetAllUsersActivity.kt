package br.senai.sp.jandira.vanbora.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.api.calls.user.UserCall
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import br.senai.sp.jandira.vanbora.model.user.UserList
import br.senai.sp.jandira.vanbora.model.user.UserModel
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllUsersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                Log.i("ds3m", "aqui ******************")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUi = rememberSystemUiController()
                    SideEffect {
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0),
                            darkIcons = true
                        )
                    }
                    Log.i("ds3m", "aqui ******************")
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    Log.i("ds3m", "aqui ******************")

    val retrofit = RetrofitApi.getRetrofit()
    val usersCall = retrofit.create(UserCall::class.java)
    val call = usersCall.getAllUsers()

    var users by remember {
        mutableStateOf(UserList(listOf()))
    }

    call.enqueue(object: Callback<UserList>{
        override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
            users = response.body()!!
            Log.i("ds3m", users.toString())
        }

        override fun onFailure(call: Call<UserList>, t: Throwable) {
            Log.i("ds3m", t.message.toString())
        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color.Green)
        ) {
            items(users.users!!) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(197, 152, 22, 255)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = ""
                            )
                        }
                        Column {
                            Text(text = "add", color = Color.White)
                            Text(text = "add2", color = Color.White)
                            Text(text = "add3", color = Color.White)
                        }
                    }
                }
            }
        }


    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        Greeting()
    }
}