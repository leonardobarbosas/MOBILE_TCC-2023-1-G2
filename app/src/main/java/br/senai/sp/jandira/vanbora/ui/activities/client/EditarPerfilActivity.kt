package br.senai.sp.jandira.vanbora.ui.activities.client

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.components.forms.user.getImageDisplayNameFromUri
import br.senai.sp.jandira.vanbora.components.functions.MaskTransformationCep
import br.senai.sp.jandira.vanbora.components.headers.Rotas.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.model.user.User
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
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

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditarPerfil() {

    val scrollState = rememberScrollState()


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

    var userEdit by remember {
        mutableStateOf(User())
    }

    var fisicoClick by remember {
        mutableStateOf(true)
    }
    val calendarState = rememberSheetState()
    var birthdayState by rememberSaveable {
        mutableStateOf("Ano-Mes-Dia")
    }

    val context = LocalContext.current

    val intent = (context as EditarPerfilActivity).intent

    val idPerfil = intent.getStringExtra("id")


    val perfilCall = GetFunctionsCall.getUserCall().getUserById(id = idPerfil.toString())

    var perfil by remember {
        mutableStateOf(User())
    }

    var code by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    perfilCall.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful){
                perfil = response.body()!!
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }
    })

    var storage = FirebaseStorage.getInstance()

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var imageIcon by remember {
        mutableStateOf<Painter?>(null)
    }

    var succesImg by remember {
        mutableStateOf(1)
    }

    var urlImage by remember {
        mutableStateOf("")
    }

    val gallerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            succesImg = 2

            selectedImage = uri

            val imageName = getImageDisplayNameFromUri(context, selectedImage!!)


            val mountainsRef =
                storage.reference.child("users-profile-picture/${imageName.toString()}")

            val uploadTask = mountainsRef.putFile(selectedImage!!)

            uploadTask.addOnSuccessListener {
                mountainsRef.downloadUrl.addOnSuccessListener { url ->
                    urlImage = url.toString()
                }
            }

        })
    if (succesImg == 1) {
        imageIcon = painterResource(id = R.drawable.baseline_linked_camera_24_back)
    } else if (succesImg == 2) {
        imageIcon = rememberAsyncImagePainter(model = selectedImage)
    }

    Column {


        HeaderSelectDriverComplement()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background2),
                    contentScale = ContentScale.Crop
                )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(painter = imageIcon!!, contentDescription = "", tint = Color.Unspecified, modifier = Modifier
                .clickable {
                    gallerLauncher.launch("image/*")
                }
                .size(200.dp)
            )

            //Nome
            OutlinedTextField(
                value = nomeState, onValueChange = {
                    nomeState = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(text = stringResource(id = R.string.name))
                },
                placeholder = {
                    perfil?.let {
                        Text(
                            text = perfil.nome,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            //Email
            OutlinedTextField(
                value = emailState, onValueChange = {
                    emailState = it
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
                            text = perfil.email,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

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
                            text = perfil.rg,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            //CPF
            OutlinedTextField(
                value = cpfState, onValueChange = {
                    cpfState = it
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
                            text = perfil.cpf,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            //CEP
            OutlinedTextField(
                value = cepState, onValueChange = {
                    cepState
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
                            text = perfil.cep,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = MaskTransformationCep(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            //TELEFONE
            OutlinedTextField(
                value = telefoneState, onValueChange = {
                    telefoneState = it
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
                            text = perfil.telefone,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            //Data Nascimento
            AnimatedVisibility(
                visible = fisicoClick,
                enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
                exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
            ) {
                Column {
                    CalendarDialog(
                        state = calendarState,
                        config = CalendarConfig(
                            yearSelection = true
                        ),
                        selection = CalendarSelection.Date { birthdate ->
                            birthdayState = birthdate.toString()
                            dataNascimentoState = birthdayState
                        }
                    )

                    androidx.compose.material3.Button(
                        onClick = {
                            calendarState.show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, end = 50.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            Color(231, 175, 64, 255)
                        ),
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        ),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        androidx.compose.material3.Text(
                            text = "Selecione a data de nascimento",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    androidx.compose.material3.Text(
                        text = birthdayState,
                        modifier = Modifier
                            .padding(start = 50.dp, end = 50.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                            .height(30.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(
                    onClick = {
                        if (nomeState == ""){
                            nomeState = perfil.nome
                        }
                        if (emailState == ""){
                            emailState = perfil.email
                        }
                        if (rgState == ""){
                            rgState = perfil.rg
                        }
                        if (cpfState == ""){
                            cpfState = perfil.cpf
                        }
                        if (cepState == ""){
                            cepState = perfil.cep
                        }
                        if (telefoneState == ""){
                            telefoneState = perfil.telefone
                        }
                        if (birthdayState == ""){
                            birthdayState = perfil.data_nascimento
                        }
                        if (dataNascimentoState == ""){
                            dataNascimentoState = perfil.data_nascimento
                        }
                        if (urlImage == ""){
                            urlImage = perfil.foto
                        }
                        isSenhaError = senhaState.length == 0

                        var user = User(
                            cepState,
                            cpfState,
                            dataNascimentoState,
                            emailState,
                            urlImage,
                            perfil!!.id,
                            nomeState,
                            rgState,
                            senhaState,
                            telefoneState,
                            status_usuario = 1
                        )


                        var perfilPutCall = GetFunctionsCall.getUserCall().putUser(user.id.toString(), user)

                        perfilPutCall.enqueue(object : Callback<String> {
                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                code = response.code().toString()
                                message = response.body().toString()
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.i("ds3m", "onFailure: $t")
                            }
                        })

                        if (code != "201") {
                            Toast.makeText(context, "Verifique os dados e envie novamente!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                context, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT
                            )
                                .show()

                            if (user != null) {
                                val intentSelect = Intent(context, MotoristasActivity::class.java)
                                intentSelect.putExtra("id", user.id.toString())
                                context.startActivity(intentSelect)
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.save)
                    )
                }

                Button(
                    onClick = {
                        var user = User(
                            cepState,
                            cpfState,
                            dataNascimentoState,
                            emailState,
                            urlImage,
                            perfil!!.id,
                            nomeState,
                            rgState,
                            senhaState,
                            telefoneState,
                            status_usuario = 1
                        )

                        val callUserDelete = GetFunctionsCall.getUserCall().deleteUser(user.id)
                        callUserDelete.enqueue(object: Callback<String>{
                            override fun onResponse(
                                call: Call<String>, response: Response<String>
                            ) {
                                Toast.makeText(context, "Usuário deletado com sucesso", Toast.LENGTH_SHORT).show()
                                val intentSelect = Intent(context, MainActivity::class.java)
                                context.startActivity(intentSelect)
                            }
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.i("ds3m", "onFailure: $t")
                            }

                        })
                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.delete)
                    )
                }

            }

        }

    }
}

