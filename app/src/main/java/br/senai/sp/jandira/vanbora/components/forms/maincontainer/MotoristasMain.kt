package br.senai.sp.jandira.vanbora.components.forms.maincontainer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.UserList
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MotoristasMain() {

    val usersCall = GetFunctionsCall.getUserCall().getAllUsers()

    var users by remember {
        mutableStateOf(UserList(listOf()))
    }

    usersCall.enqueue(object : Callback<UserList> {
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {
                items(users.users) {
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