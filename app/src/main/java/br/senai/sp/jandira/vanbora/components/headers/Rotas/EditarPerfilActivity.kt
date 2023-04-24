package br.senai.sp.jandira.vanbora.components.headers.Rotas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

    var nomeState by rememberSaveable() {
        mutableStateOf("")
    }

    var isNomeError by remember() {
        mutableStateOf(false)
    }

    var emailState by rememberSaveable() {
        mutableStateOf("")
    }

    var isEmailError by remember() {
        mutableStateOf(false)
    }

    var senhaState by rememberSaveable() {
        mutableStateOf("")
    }

    var isSenhaError by remember() {
        mutableStateOf(false)
    }


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

    Column {

        HeaderSelectDriverComplement(
            context = context, componentActivity = localizeMain.newInstance()
        )


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

            //Nome
            OutlinedTextField(
                value = nomeState, onValueChange = {
                    nomeState = it

                    if (it == "" || it == null) {
                        isNomeError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.nome_passageiro))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.nome,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isNomeError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isNomeError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isNomeError) {
                Text(
                    text = stringResource(id = R.string.nome_passageiro_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //Email
            OutlinedTextField(
                value = emailState, onValueChange = {
                    emailState = it

                    if (it == "" || it == null) {
                        isEmailError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.email,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isEmailError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isEmailError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isEmailError) {
                Text(
                    text = stringResource(id = R.string.email_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //Senha
            OutlinedTextField(
                value = senhaState, onValueChange = {
                    senhaState = it

                    if (it == "" || it == null) {
                        isSenhaError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.senha))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = "*******",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isSenhaError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isSenhaError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isSenhaError) {
                Text(
                    text = stringResource(id = R.string.senha_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //RG
            OutlinedTextField(
                value = rgState, onValueChange = {
                    rgState = it

                    if (it == "" || it == null) {
                        isRgError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.rg))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.rg,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isRgError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isRgError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isRgError) {
                Text(
                    text = stringResource(id = R.string.rg_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //CPF
            OutlinedTextField(
                value = cpfState, onValueChange = {
                    cpfState = it

                    if (it == "" || it == null) {
                        isCpfError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.cpf))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.cpf,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isCpfError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isCpfError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isCpfError) {
                Text(
                    text = stringResource(id = R.string.cpf_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //CEP
            OutlinedTextField(
                value = cepState, onValueChange = {
                    cepState = it

                    if (it == "" || it == null) {
                        cepState
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.cep))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.cep,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isCepError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isCepError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isCepError) {
                Text(
                    text = stringResource(id = R.string.cep_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //TELEFONE
            OutlinedTextField(
                value = telefoneState, onValueChange = {
                    telefoneState = it

                    if (it == "" || it == null) {
                        isTelefoneError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.telefone))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.telefone,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isTelefoneError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isTelefoneError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isTelefoneError) {
                Text(
                    text = stringResource(id = R.string.telefone_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            //Data de Nascimento
            OutlinedTextField(
                value = dataNascimentoState, onValueChange = {
                    dataNascimentoState = it

                    if (it == "" || it == null) {
                        isDataNascimentoError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.data_nascimento))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = it.data_nascimento,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (isDataNascimentoError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isDataNascimentoError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isDataNascimentoError) {
                Text(
                    text = stringResource(id = R.string.data_nascimento_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.padding(26.dp))

            Button(
                onClick = {

                    var user = User(cepState, cpfState, dataNascimentoState, emailState, perfil!!.foto, perfil!!.id, nomeState, rgState, senhaState, telefoneState, status_usuario = 1)

                    Log.i("ds3m", "EditarPerfil: ${user}")


                    var perfilPutCall = GetFunctionsCall.getUserCall().putUser(user.id.toString(), user)

                    perfilPutCall.enqueue(object: Callback<String>{
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            code = response.code().toString()
                            message = response.body().toString()
                            Log.i("ds3m", "onResponse: $code , $message")
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.i("ds3m", "onFailure: $t")
                        }
                    })

                    if(code == "201"){
                        context.startActivity(Intent(context, MotoristasActivity::class.java))
                    }
                    else{
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        Log.i("ds3m", "EditarPerfil: tessste")
                    }

                },
                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
            ) {
                Text(
                    text = stringResource(id = R.string.save)
                )
            }

        }

    }
}
