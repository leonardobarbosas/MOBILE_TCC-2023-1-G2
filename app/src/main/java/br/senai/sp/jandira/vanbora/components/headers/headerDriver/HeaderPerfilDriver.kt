package br.senai.sp.jandira.vanbora.components.headers.headerDriver

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.driver.EditarPerfilDriver
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeaderPerfilDriver() {

    val context = LocalContext.current

    val intent = (context as EditarPerfilDriver).intent

    val idPerfil = intent.getStringExtra("id")

    val perfilCall = GetFunctionsCall.getUserCall().getUserById(id = idPerfil.toString())

    var perfil by remember {
        mutableStateOf<User?>(null)
    }



    perfilCall.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            perfil = response.body()!!
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }
    })

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clickable {

                    if (perfil != null) {
                        val intentSelect = Intent(context, SuasVansActivity::class.java)
                        intentSelect.putExtra("id", perfil?.id.toString())
                        context.startActivity(intentSelect)
                    }

                }
        )



        Button(
            onClick = {
                val intentSelect = Intent(context, MainActivity::class.java)
                context.startActivity(intentSelect)
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
        ) {
            Text(
                text = stringResource(id = R.string.logout)
            )
        }
    }
}