package br.senai.sp.jandira.vanbora.components.forms.driver

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.ui.activities.driver.VanComplements
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson

@Composable
fun DriverInfos(name: String, email: String, senha: String) {

    var rgState by rememberSaveable() {
        mutableStateOf("")
    }

    var cpfState by rememberSaveable() {
        mutableStateOf("")
    }

    var cepState by rememberSaveable() {
        mutableStateOf("")
    }

    var cnhState by rememberSaveable() {
        mutableStateOf("")
    }

    var telefoneState by rememberSaveable() {
        mutableStateOf("")
    }

    var dataNascimentoState by rememberSaveable() {
        mutableStateOf("")
    }

    var inicioCarreiraState by rememberSaveable() {
        mutableStateOf("")
    }
    var descricaoState by rememberSaveable() {
        mutableStateOf("")
    }


    var isRgError by remember() {
        mutableStateOf(false)
    }

    var isCpfError by remember() {
        mutableStateOf(false)
    }

    var isCepError by remember() {
        mutableStateOf(false)
    }

    var isCnhError by remember() {
        mutableStateOf(false)
    }

    var isTelefoneError by remember() {
        mutableStateOf(false)
    }

    var isDataNascimentoError by remember() {
        mutableStateOf(false)
    }
    var isInicioCarreiraError by remember() {
        mutableStateOf(false)
    }
    var isDescricaoError by remember() {
        mutableStateOf(false)
    }

    var context = LocalContext.current

    var imageIcon by remember {
        mutableStateOf<Painter?>(null)
    }
    var succesImg by remember {
        mutableStateOf(1)
    }

    var urlImage by remember {
        mutableStateOf("")
    }

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var storage = FirebaseStorage.getInstance()

    val gallerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            succesImg = 2

            selectedImage = uri

            val imageName =
                br.senai.sp.jandira.vanbora.components.forms.user.getImageDisplayNameFromUri(
                    context,
                    selectedImage!!
                )
            Log.i("ds3m", "DriverInfos: ${imageName.toString()}")

            val mountainsRef =
                storage.reference.child("drivers-profile-picture/${imageName.toString()}")

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

    //Main
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //image
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {



            Icon(painter = imageIcon!!, contentDescription = "", modifier = Modifier
                .clickable {
                    gallerLauncher.launch("image/*")
                }
                .size(200.dp)
            )
        }

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
                Text(
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
                Text(
                    text = stringResource(id = R.string.cpf),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                    isCepError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.cep),
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isCepError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isCepError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

        //CNH
        OutlinedTextField(
            value = cnhState, onValueChange = {
                cnhState = it

                if (it == "" || it == null) {
                    isCnhError
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.cnh),
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            trailingIcon = {
                if (isCnhError) Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
                )
            },
            isError = isCnhError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if (isCnhError) {
            Text(
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
                Text(
                    text = stringResource(id = R.string.telefone),
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

        //DESCRICAO
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
                Text(
                    text = stringResource(id = R.string.descricao),
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
            Text(
                text = stringResource(id = R.string.inicio_carreira_error),
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
                Text(
                    text = stringResource(id = R.string.inicio_carreira),
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
            Text(
                text = stringResource(id = R.string.inicio_carreira_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }


        //DATA NASCIMENTO
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
                Text(
                    text = stringResource(id = R.string.data_nascimento),
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

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = {
                val intentSelect = Intent(context, VanComplements::class.java)

                val driverPost = DriverPost(
                    avaliacao = 10,
                    cnh = cnhState,
                    cpf = cpfState,
                    data_nascimento = dataNascimentoState,
                    descricao = descricaoState,
                    email = email,
                    foto = urlImage,
                    id_preco = 0,
                    inicio_carreira = inicioCarreiraState,
                    nome = name,
                    rg = rgState,
                    senha = senha,
                    telefone = telefoneState
                )

//                intentSelect.putExtra("descricao", descricaoState)
//                intentSelect.putExtra("telefone", telefoneState)
//                intentSelect.putExtra("inicio_carreira", inicioCarreiraState)
//                intentSelect.putExtra("data_nascimento", dataNascimentoState)
                intentSelect.putExtra("driver", Gson().toJson(driverPost))

                context.startActivity(intentSelect)
            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

        ) {
            Text(
                text = stringResource(id = R.string.next)
            )
        }

    }
}