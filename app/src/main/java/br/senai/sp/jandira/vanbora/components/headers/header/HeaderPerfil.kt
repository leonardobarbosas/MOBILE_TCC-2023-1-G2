package br.senai.sp.jandira.vanbora.components.headers

import android.content.Intent
import android.util.Log
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
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.HeaderCadastroLogin
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristaPerfilActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeaderPerfil(){

    val context = LocalContext.current

    val intent = (context as MotoristaPerfilActivity).intent

    val idPerfil = intent.getStringExtra("id_usuario")

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
                            if (perfil != null) {
                                val intentSelect = Intent(context, MotoristasActivity::class.java)
                                intentSelect.putExtra("id", perfil?.id.toString())
                                context.startActivity(intentSelect)
                            }

                    }
            )

            HeaderCadastroLogin()
        }
    }
}