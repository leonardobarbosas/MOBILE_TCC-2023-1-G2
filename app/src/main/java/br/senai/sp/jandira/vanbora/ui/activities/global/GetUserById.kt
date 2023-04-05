package br.senai.sp.jandira.vanbora.ui.activities.global

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.ui.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserById : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetId()
                }
            }
        }
    }
}

@Composable
fun GetId() {

    var context = LocalContext.current

    var idState by remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var user by remember {
            mutableStateOf<User?>(null)
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Busque o Id"
            )
            OutlinedTextField(
                value = idState,
                onValueChange = {
                    idState = it
                },
                label = {
                    Text(text = "Insira o Id")
                }
            )
            Button(
                onClick = {

                    var call = GetFunctionsCall.getUserCall().getUserById(id = idState.toInt())

                    call.enqueue(object : Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            user = response.body()!!
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.i("ds3m", "onFailure: sem net")
                        }
                    })

                }) {
                Text(text = "Procurar")
            }
        }

        Column {
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
                            painter = rememberAsyncImagePainter(user?.foto),
                            contentDescription = null,
                            modifier = Modifier.size(128.dp)
                        )
                    }
                    Column {
                        user?.let { Text(text = it.nome, color = Color.White) }
                        user?.let { Text(text = it.email, color = Color.White) }
                        user?.let { Text(text = it.telefone, color = Color.White) }
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VanboraTheme {
        GetId()
    }
}