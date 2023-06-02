package br.senai.sp.jandira.vanbora.components.headers.headerDriver

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import br.senai.sp.jandira.vanbora.components.headers.Logo
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.EditarPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.driver.EditarPerfilDriver
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HeaderMotorista() {

    val context = LocalContext.current

    val intent = (context as SuasVansActivity).intent

    val idLogin = intent.getStringExtra("id")

    val loginCall = GetFunctionsCall.getDriverCall().getDriverById(id = idLogin.toString())

    var login by remember {
        mutableStateOf<Driver?>(null)
    }

    loginCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            login = response.body()!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure jheader motorista")
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
                .clickable {

                    val intentSelect = Intent(context, EditarPerfilDriver::class.java)

                    intentSelect.putExtra("id", login?.id.toString())

                    login?.id.toString() to EditarPerfilDriver::class.java

                    context.startActivity(intentSelect)

                }
        )


    }

}
