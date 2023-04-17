package br.senai.sp.jandira.vanbora.components.headers.Rotas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.components.headers.Rotas.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.PerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarPerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EditarPerfil()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditarPerfil() {

    var rgState by rememberSaveable() {
        mutableStateOf("")
    }

    var isRgError by remember() {
        mutableStateOf(false)
    }

    var cpfState by rememberSaveable() {
        mutableStateOf("")
    }

    var isCpfError by remember() {
        mutableStateOf(false)
    }

    var cepState by rememberSaveable() {
        mutableStateOf("")
    }

    var isCepError by remember() {
        mutableStateOf(false)
    }

    var telefoneState by rememberSaveable() {
        mutableStateOf("")
    }

    var isTelefoneError by remember() {
        mutableStateOf(false)
    }

    var dataNascimentoState by rememberSaveable() {
        mutableStateOf("")
    }

    var isDataNascimentoError by remember() {
        mutableStateOf(false)
    }


    val localizeMain by remember {
        mutableStateOf(MotoristasActivity::class.java)
    }
    
    val context = LocalContext.current

    context as PerfilActivity

//    val context = LocalContext.current
//
//    val intent = (context as PerfilActivity).intent
//
//    val id = context.parent.intent.getStringExtra("id")
//
//    val idPerfil = intent.getStringExtra("id")
//
//    Log.i("ds3m", "EditarPerfil: $id")
//
//    val perfilCall = GetFunctionsCall.getUserCall().getUserById(id = idPerfil.toString())
//
//    var perfil by remember {
//        mutableStateOf<User?>(null)
//    }
//
//    perfilCall.enqueue(object : Callback<User> {
//        override fun onResponse(call: Call<User>, response: Response<User>) {
//            perfil = response.body()!!
//        }
//
//        override fun onFailure(call: Call<User>, t: Throwable) {
//            Log.i("ds3m", "onFailure")
//        }
//    })

//    HeaderSelectDriverComplement(context = context, componentActivity = localizeMain.newInstance())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//
//    //RG
//        OutlinedTextField(
//            value = rgState, onValueChange = {
//                rgState = it
//
//                if (it == "" || it == null) {
//                    isRgError
//                }
//
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
//            label = {
//                perfil?.let {
//                    Text(
//                        text = it.rg,
//                        textAlign = TextAlign.Center,
//                        style = TextStyle(
//                            color = Color.Black,
//                        )
//                    )
//                }
//            },
//            trailingIcon = {
//                if (isRgError) Icon(
//                    imageVector = Icons.Default.Warning,
//                    contentDescription = ""
//                )
//            },
//            isError = isRgError,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0, 0, 0, 255),
//                unfocusedBorderColor = Color(0, 0, 0, 255)
//            )
//        )
//        if (isRgError) {
//            Text(
//                text = stringResource(id = R.string.rg_error),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(end = 52.dp),
//                color = Color.Red,
//                fontSize = 15.sp,
//                textAlign = TextAlign.End
//            )
//        }
//
//
//
//
//
//        Spacer(modifier = Modifier.padding(16.dp))
//
//        Button(
//            onClick = {
//                context.startActivity(Intent(context, MotoristasActivity::class.java))
//            },
//            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
//        ) {
//            Text(
//                text = stringResource(id = R.string.save)
//            )
//        }

    }

}

