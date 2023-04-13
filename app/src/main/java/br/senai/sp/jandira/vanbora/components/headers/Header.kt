package br.senai.sp.jandira.vanbora.components.headers

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Header() {

    val context = LocalContext.current

    val intent = (context as MotoristasActivity).intent

    val idLogin = intent.getStringExtra("id")

    val loginCall = GetFunctionsCall.getUserCall().getUserById(id = idLogin.toString())

    var login by remember {
        mutableStateOf<User?>(null)
    }

    loginCall.enqueue(object : Callback<User>{
        override fun onResponse(call: Call<User>, response: Response<User>) {
            login = response.body()!!
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }

    })

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo()


        Image(
            painter = rememberAsyncImagePainter(login?.foto),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )


    }

}


