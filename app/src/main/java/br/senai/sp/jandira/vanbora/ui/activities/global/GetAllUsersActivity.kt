package br.senai.sp.jandira.vanbora.ui.activities.global

import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.api.calls.user.UserCall
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.UserList
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllUsersActivity : ComponentActivity() {
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
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0),
                            darkIcons = true
                        )
                    }
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    var nomeState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }
    var telefoneState by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current


    val usersCall = GetFunctionsCall.getUserCall().getAllUsers()

    var users by remember {
        mutableStateOf(UserList(listOf()))
    }

    usersCall.enqueue(object : Callback<UserList>{
        override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
            users = response.body()!!
            Log.i("ds3m", "onResponse: deu certo")
        }

        override fun onFailure(call: Call<UserList>, t: Throwable) {
            Log.i("ds3m", "onFailure: sem net")
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
            Text(
                text = "Cadastro de Usuários"
            )
            OutlinedTextField(
                value = nomeState,
                onValueChange = {
                    nomeState = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Nome"
                    )
                }
            )
            OutlinedTextField(
                value = emailState,
                onValueChange = {
                    emailState = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Email"
                    )
                }
            )
            OutlinedTextField(
                value = telefoneState,
                onValueChange = {
                    telefoneState = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Telefone"
                    )
                }
            )

            Button(
                onClick = {
                    context.startActivity(Intent(context, GetUserById::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(197, 152, 22, 255))
            ) {
                Text(
                    text = "Cadastrar"
                )
            }

            Spacer(modifier = Modifier.padding(bottom = 32.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ){
                items(users.users!!){
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
                                    painter = rememberAsyncImagePainter(it.foto),
                                    contentDescription = null,
                                    modifier = Modifier.size(128.dp)
                                )
                            }
                            Column {
                                Text(text = it.nome, color = Color.White)
                                Text(text = it.email, color = Color.White)
                                Text(text = it.telefone, color = Color.White)

                            }
                       }
                    }
                }
            }
        }

    }
}

//{
//                items(users.users!!) {
//                    Card(
//
//                    ) {
//
//                    }
//                }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        Greeting()
    }
}