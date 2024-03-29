package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import br.senai.sp.jandira.vanbora.components.forms.user.getImageDisplayNameFromUri
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderPerfilDriver
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPut
import br.senai.sp.jandira.vanbora.model.prices.AllPrices
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarPerfilDriver : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditarDriver()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@SuppressLint("RememberReturnType")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditarDriver() {

    val context = LocalContext.current

    val scrollState = rememberScrollState()


    var nomeState by remember {
        mutableStateOf("")
    }
    var isNomeError by remember {
        mutableStateOf(false)
    }
    var emailState by remember {
        mutableStateOf("")
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    var senhaState by remember {
        mutableStateOf("")
    }
    var isSenhaError by remember {
        mutableStateOf(false)
    }
    var rgState by remember {
        mutableStateOf("")
    }
    var isRgError by remember {
        mutableStateOf(false)
    }
    var cpfState by remember {
        mutableStateOf("")
    }
    var isCpfError by remember {
        mutableStateOf(false)
    }
    var cnhState by remember {
        mutableStateOf("")
    }
    var isCnhState by remember {
        mutableStateOf(false)
    }
    var telefoneState by remember {
        mutableStateOf("")
    }
    var isTelefoneError by remember {
        mutableStateOf(false)
    }
    var inicioCarreiraState by remember {
        mutableStateOf("")
    }
    var isInicioCarreiraError by remember {
        mutableStateOf(false)
    }
    var dataNascimentoState by remember {
        mutableStateOf("")
    }
    var isDataNascimentoError by remember {
        mutableStateOf(false)
    }
    var precoState by remember {
        mutableStateOf("")
    }
    var isPriceError by remember {
        mutableStateOf(false)
    }
    var idPrice by remember {
        mutableStateOf(0)
    }
    var descricaoState by remember {
        mutableStateOf("")
    }
    var isDescricaoError by remember {
        mutableStateOf(false)
    }
    var fisicoClick by remember {
        mutableStateOf(true)
    }
    var inicioClick by remember {
        mutableStateOf(true)
    }
    val calendarState = rememberSheetState()
    val calendarStateInicio = rememberSheetState()
    var birthdayState by rememberSaveable {
        mutableStateOf("Ano-Mes-Dia")
    }
    var inicioState by rememberSaveable {
        mutableStateOf("Ano-Mes-Dia")
    }


    var code by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    val pricesCall = GetFunctionsCall.getPricesCall().getAllPrices()

    var prices by remember {
        mutableStateOf<AllPrices?>(null)
    }

    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    val icon = if (isMenuExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    pricesCall.enqueue(object : Callback<AllPrices> {
        override fun onResponse(call: Call<AllPrices>, response: Response<AllPrices>) {
            if (response.isSuccessful) {
                prices = response.body()!!
            }
        }

        override fun onFailure(call: Call<AllPrices>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message}")
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


    val intent = (context as EditarPerfilDriver).intent

    val idPerfil = intent.getStringExtra("id")
    Log.i("ds3m", "ver se id driver ta chegando: $idPerfil")

    val perfilCall = GetFunctionsCall.getDriverCall().getDriverById(id = idPerfil.toString())

    var perfil by remember {
        mutableStateOf<Driver?>(null)
    }

    perfilCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            if (response.isSuccessful) {
                perfil = response.body()!!
            }
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure idDriver: $t")
        }

    })


    HeaderPerfilDriver()

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

        Icon(painter = imageIcon!!,
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable {
                    gallerLauncher.launch("image/*")
                }
                .size(200.dp)
        )
        //NOME
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.name),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.nome,
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
            androidx.compose.material.Text(
                text = stringResource(id = R.string.name_error),
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.email),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.email,
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
            androidx.compose.material.Text(
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.senha),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
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
            androidx.compose.material.Text(
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.rg),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.rg,
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
            androidx.compose.material.Text(
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.cpf),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.cpf,
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
            androidx.compose.material.Text(
                text = stringResource(id = R.string.cpf_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }
        //CNH
        OutlinedTextField(
            value = cnhState, onValueChange = {
                cnhState = it

                if (it == "" || it == null) {
                    isCnhState
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.cnh),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.cnh,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                }
            },
            trailingIcon = {
                if (isCnhState) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isCnhState,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isCnhState) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.cnh_error),
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.telefone),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.telefone,
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isTelefoneError) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.telefone_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }


        //Preco
        Column() {
            OutlinedTextField(
                value = precoState, onValueChange = {
                    precoState = it

                    if (it == "" || it == null) {
                        isPriceError
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                readOnly = true,
                label = {
                    androidx.compose.material.Text(
                        text = stringResource(id = R.string.faixa_preco),
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { isMenuExpanded = !isMenuExpanded })
                    if (isPriceError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isPriceError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )

            DropdownMenu(expanded = isMenuExpanded, onDismissRequest = {
                isMenuExpanded = false
            }) {
                prices!!.prices.forEach {
                    DropdownMenuItem(onClick = {
                        idPrice = it.id
                        precoState = it.faixa_preco
                        isMenuExpanded = false

                    }) {
                        androidx.compose.material.Text(text = it.faixa_preco)
                    }
                }
            }
        }

        //Descricao
        OutlinedTextField(
            value = descricaoState, onValueChange = {
                descricaoState = it

                if (it == "" || it == null) {
                    isDescricaoError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.descricao),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            placeholder = {
                perfil?.let {
                    androidx.compose.material.Text(
                        text = perfil!!.descricao,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                }
            },
            trailingIcon = {
                if (isDescricaoError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isDescricaoError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isDescricaoError) {
            androidx.compose.material.Text(
                text = "Descricao inválida",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        //inicio Carreira
        AnimatedVisibility(
            visible = inicioClick,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            Column {
                CalendarDialog(
                    state = calendarStateInicio,
                    config = CalendarConfig(
                        yearSelection = true
                    ),
                    selection = CalendarSelection.Date { iniciodate ->
                        inicioState = iniciodate.toString()
                        inicioCarreiraState = inicioState
                    }
                )

                Button(
                    onClick = {
                        calendarStateInicio.show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp),
                    colors = ButtonDefaults.buttonColors(
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
                    Text(
                        text = "Selecione o inicio de carreira",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = inicioState,
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

                Button(
                    onClick = {
                        calendarState.show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp),
                    colors = ButtonDefaults.buttonColors(
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
                    Text(
                        text = "Selecione a data de nascimento",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Text(
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    if (nomeState == "") {
                        nomeState = perfil!!.nome
                    }
                    if (emailState == "") {
                        emailState = perfil!!.email
                    }
                    if (rgState == "") {
                        rgState = perfil!!.rg
                    }
                    if (cpfState == "") {
                        cpfState = perfil!!.cpf
                    }
                    if (cnhState == "") {
                        cnhState = perfil!!.cnh
                    }
                    if (telefoneState == "") {
                        telefoneState = perfil!!.telefone
                    }
                    if (birthdayState == "") {
                        birthdayState = perfil!!.data_nascimento
                    }
                    if (inicioState == "") {
                        inicioState = perfil!!.inicio_carreira
                    }
                    if (urlImage == "") {
                        urlImage = perfil!!.foto
                    }
                    if (descricaoState == "") {
                        descricaoState = perfil!!.descricao
                    }
                    isSenhaError = senhaState.length == 0
                    isPriceError = precoState.length == 0

                    val driver = DriverPut(
                        perfil!!.id,
                        perfil!!.avaliacao.toInt(),
                        cnhState,
                        cpfState,
                        dataNascimentoState,
                        descricaoState,
                        emailState,
                        urlImage,
                        idPrice,
                        inicioCarreiraState,
                        nomeState,
                        rgState,
                        senhaState,
                        telefoneState,
                        status_motorista = 1
                    )

                    var perfilPutCall =
                        GetFunctionsCall.getDriverCall().putDriver(driver.id.toString(), driver)

                    perfilPutCall.enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            code = response.code().toString()
                            message = response.body().toString()
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.i("ds3m", "onFailure perfilPutCall: $t")
                        }
                    })

                    if (code != "201") {
                        Toast.makeText(
                            context,
                            "Verifique os dados e envie novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(context, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT)
                            .show()

                        if (driver != null) {
                            val intentSelect = Intent(context, SuasVansActivity::class.java)
                            intentSelect.putExtra("id", driver.id.toString())
                            context.startActivity(intentSelect)
                        }
                    }

                },
                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
            ) {
                Text(text = stringResource(id = R.string.save))
            }
            Button(
                onClick = {
                    val driver = DriverPut(
                        perfil!!.id,
                        perfil!!.avaliacao.toInt(),
                        cnhState,
                        cpfState,
                        dataNascimentoState,
                        descricaoState,
                        emailState,
                        urlImage,
                        idPrice,
                        inicioCarreiraState,
                        nomeState,
                        rgState,
                        senhaState,
                        telefoneState,
                        status_motorista = 1
                    )

                    val callDriverDelete = GetFunctionsCall.getDriverCall().deleteDriver(driver.id)
                    callDriverDelete.enqueue(object : Callback<String> {
                        override fun onResponse(
                            call: Call<String>, response: Response<String>
                        ) {
                            Toast.makeText(
                                context,
                                "Usuário deletado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intentSelect = Intent(context, MainActivity::class.java)
                            context.startActivity(intentSelect)
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.i("ds3m", "onFailure: $t")
                        }

                    })
                },
                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
            ) {
                Text(text = stringResource(id = R.string.delete))
            }
        }


    }

}
