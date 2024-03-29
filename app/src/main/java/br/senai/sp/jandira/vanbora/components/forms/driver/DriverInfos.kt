package br.senai.sp.jandira.vanbora.components.forms.driver

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.model.prices.AllPrices
import br.senai.sp.jandira.vanbora.ui.activities.driver.VanComplements
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DriverInfos(name: String, email: String, senha: String) {

    var maxChar = 10

    var rgState by rememberSaveable() {
        mutableStateOf("")
    }

    var cpfState by rememberSaveable() {
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

    val pricesCall = GetFunctionsCall.getPricesCall().getAllPrices()

    var prices by remember {
        mutableStateOf(AllPrices(listOf()))
    }

    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Icon(painter = imageIcon!!, contentDescription = "",tint = Color.Unspecified, modifier = Modifier
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

                val cleanInput = it.replace("\\D".toRegex(), "")
                cpfState = formatCPF(cleanInput)

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

                val cleanInput = it.replace("\\D".toRegex(), "")
                telefoneState = formatPhone(cleanInput)

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

                androidx.compose.material3.Button(
                    onClick = {
                        calendarStateInicio.show()
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
                        text = "Selecione o inicio de carreira",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                androidx.compose.material3.Text(
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

        var priceState by remember {
            mutableStateOf("")
        }
        var idPrice by remember {
            mutableStateOf(0)
        }

        val icon = if (isMenuExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        //Preço Serviço
        Column() {
            OutlinedTextField(
                value = priceState, onValueChange = {
                    priceState = it

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                readOnly = true,
                label = {
                    Text(
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
                prices.prices.forEach {
                    DropdownMenuItem(onClick = {
                        idPrice = it.id
                        priceState = it.faixa_preco
                        isMenuExpanded = false

                    }) {
                        Text(text = it.faixa_preco)
                    }
                }
            }
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
                    id_preco = idPrice,
                    inicio_carreira = inicioCarreiraState,
                    nome = name,
                    rg = rgState,
                    senha = senha,
                    telefone = telefoneState
                )

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

//fun dateFilter(text: AnnotatedString): TransformedText {
//
//    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
//    var out = ""
//    for (i in trimmed.indices) {
//        out += trimmed[i]
//        if (i % 2 == 1 && i < 4) out += "/"
//    }
//
//    val numberOffsetTranslator = object : OffsetMapping {
//        override fun originalToTransformed(offset: Int): Int {
//            if (offset <= 1) return offset
//            if (offset <= 3) return offset +1
//            if (offset <= 8) return offset +2
//            return 10
//        }
//
//        override fun transformedToOriginal(offset: Int): Int {
//            if (offset <=2) return offset
//            if (offset <=5) return offset -1
//            if (offset <=10) return offset -2
//            return 8
//        }
//    }
//
//    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
//}


fun formatPhone(phoneNumber: String): String {
    val phoneRegex = "(\\d{2})(\\d{5})(\\d{4})".toRegex()
    return phoneRegex.replace(phoneNumber, "($1) $2-$3")
}

fun formatCPF(cpf: String): String {
    val cpfRegex = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})".toRegex()
    return cpfRegex.replace(cpf, "$1.$2.$3-$4")
}

fun formatCEP(cep: String): String {
    val cepRegex = "(\\d{5})(\\d{3})".toRegex()
    return cepRegex.replace(cep, "$1-$2")
}