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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.forms.user.getImageDisplayNameFromUri
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderPerfilDriver
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.prices.AllPrices
import br.senai.sp.jandira.vanbora.ui.activities.driver.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
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
    var isPrecoError by remember {
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

    pricesCall.enqueue(object : Callback<AllPrices>{
        override fun onResponse(call: Call<AllPrices>, response: Response<AllPrices>) {
            if(response.isSuccessful){
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
            perfil = response.body()!!
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

        Icon(painter = imageIcon!!, contentDescription = "", tint = Color.Unspecified, modifier = Modifier
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
        //INICIO CARREIRA
        OutlinedTextField(
            value = inicioCarreiraState, onValueChange = {
                inicioCarreiraState = it

                if (it == "" || it == null) {
                    isInicioCarreiraError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.inicio_carreira),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isInicioCarreiraError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isInicioCarreiraError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isInicioCarreiraError) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.inicio_carreira_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }
        //Data Nascimento
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
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.data_nascimento),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isDataNascimentoError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isDataNascimentoError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isDataNascimentoError) {
            androidx.compose.material.Text(
                text = stringResource(id = R.string.data_nascimento_error),
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
                },
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {

                    val driver = Driver(
                        perfil!!.avaliacao,
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
                        telefoneState.toInt()
                    )

                    var perfilPutCall = GetFunctionsCall.getDriverCall().putDriver(driver.id.toString(), driver)

                    perfilPutCall.enqueue(object: Callback<String>{
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            code = response.code().toString()
                            message = response.body().toString()
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.i("ds3m", "onFailure perfilPutCall: $t")
                        }
                    })

                    if (code == "201") {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT
                        ).show()

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

                },
                colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
            ) {
                Text(text = stringResource(id = R.string.delete))
            }
        }


    }

}
