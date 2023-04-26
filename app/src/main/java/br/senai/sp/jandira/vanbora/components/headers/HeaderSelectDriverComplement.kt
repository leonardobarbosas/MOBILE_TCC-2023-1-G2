package br.senai.sp.jandira.vanbora.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.EditarPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.PerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeaderSelectDriverComplement(context: Context, componentActivity: ComponentActivity){

    val context = LocalContext.current

    val intent = (context as EditarPerfilActivity).intent

    val idPerfil = intent.getStringExtra("id")

    val perfilCall = GetFunctionsCall.getUserCall().getUserById(id = idPerfil.toString())

    var perfil by remember {
        mutableStateOf<User?>(null)
    }

    var code by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.background(Color.White).padding(end = 25.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
//                        context.startActivity(

//                            if (user != null) {
//                                val intentSelect = Intent(context, MotoristasActivity::class.java)
//                                intentSelect.putExtra("id", user.id.toString())
//                                context.startActivity(intentSelect)
//                            }
//
//                            Intent(
//                                context,
//                                componentActivity::class.java
//                            )
//                        )
                    }
            )

            HeaderCadastroLogin()
        }
    }
}