package br.senai.sp.jandira.vanbora.ui.activities.driver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
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
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.forms.user.getImageDisplayNameFromUri
import br.senai.sp.jandira.vanbora.components.headers.Rotas.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.ModeloList
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.van.PutVan
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarDadosVan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    EditarVan()
                }
            }
        }
    }
}


@Preview
@Composable
fun EditarVan() {

    val context = LocalContext.current

    val intent = (context as EditarDadosVan).intent

    val idDriver = intent.getStringExtra("id_motorista")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            driver = response.body()!!
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }

    })

    val idVan = intent.getStringExtra("id_van")

    val vanCall = GetFunctionsCall.getVanCall().getVanById(id = idVan.toString())

    var van by remember {
        mutableStateOf<Van?>(null)
    }

    var stateItens by remember {
        mutableStateOf(0)
    }

    vanCall.enqueue(object : Callback<Van> {
        override fun onResponse(call: Call<Van>, response: Response<Van>) {
            if (response.isSuccessful) {
                van = response.body()!!
                stateItens = 1

            }
        }

        override fun onFailure(call: Call<Van>, t: Throwable) {
            Log.i("ds3m", "onFailure")
        }

    })

    var isMenuExpanded by remember {
        mutableStateOf(false)
    }


    val modeloCall = GetFunctionsCall.getVanCall().getAllModels()

    var modeloVanList by remember {
        mutableStateOf(ModeloList(emptyList()))
    }

    var idModelo by rememberSaveable() {
        mutableStateOf(0)
    }

    var modeloState by rememberSaveable() {
        mutableStateOf("")
    }


    var placaState by rememberSaveable() {
        mutableStateOf("")
    }

    var isPlacaError by remember {
        mutableStateOf(false)
    }

    var numeroVagasState by rememberSaveable() {
        mutableStateOf("")
    }
    var succesImg by remember {
        mutableStateOf(1)
    }

    var isNumeroVagasError by remember {
        mutableStateOf(false)
    }

    var imageIcon by remember {
        mutableStateOf<Painter?>(null)
    }

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var urlImage by remember {
        mutableStateOf("")
    }

    var gallerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                var storage = FirebaseStorage.getInstance()
                selectedImage = uri
                succesImg = 2
                var imageName = getImageDisplayNameFromUri(
                    context, selectedImage!!
                )
                Log.i("ds3m", "DriverInfos: ${imageName.toString()}")

                val mountainsRef =
                    storage.reference.child("vans-profile-picture/${imageName.toString()}")

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


    if (stateItens == 1) {
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .padding(start = 80.dp, end = 80.dp)
                    .background(Color(156, 156, 156, 0)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = imageIcon!!,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { gallerLauncher.launch("image/*") }
                        .size(200.dp))
            }

            //Placa
            OutlinedTextField(value = placaState,
                onValueChange = {
                    placaState = it

                    if (it == "" || it == null) {
                        isPlacaError
                    }
                },
                placeholder = {
                    Text(text = van!!.placa)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.placa_van)
                    )
                },
                trailingIcon = {
                    if (isPlacaError) Icon(
                        imageVector = Icons.Default.Warning, contentDescription = ""
                    )
                },
                isError = isPlacaError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isPlacaError) {
                Text(
                    text = stringResource(id = R.string.isplaca_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            Column() {
                modeloCall.enqueue(object : Callback<ModeloList> {
                    override fun onResponse(
                        call: Call<ModeloList>,
                        response: Response<ModeloList>,
                    ) {
                        if (response.isSuccessful) {
                            modeloVanList = response.body()!!
                        }
                    }

                    override fun onFailure(call: Call<ModeloList>, t: Throwable) {
                        Log.i("ds3m", "onFailure: ${t.message}")
                    }
                })

                val icon = if (isMenuExpanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown

                OutlinedTextField(
                    value = modeloState,
                    onValueChange = {
                        modeloState = it

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                    readOnly = true,
                    label = {
                        Text(
                            text = stringResource(id = R.string.modelo_van), style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(icon,
                            "contentDescription",
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
                    modeloVanList.models.forEach {
                        DropdownMenuItem(onClick = {
                            idModelo = it.id
                            modeloState = it.modelo
                            isMenuExpanded = false

                        }) {
                            Text(text = it.modelo)
                        }
                    }
                }
            }

            //NumeroVagas
            OutlinedTextField(value = numeroVagasState,
                onValueChange = {
                    numeroVagasState = it
                },
                placeholder = {
                    Text(text = van!!.quantidade_vagas.toString())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                label = { Text(text = stringResource(id = R.string.numero_vagas)) },
                trailingIcon = {
                    if (isNumeroVagasError) {
                        Icon(
                            imageVector = Icons.Default.Warning, contentDescription = ""
                        )
                    }
                },
                isError = isNumeroVagasError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if (isNumeroVagasError) {
                Text(
                    text = stringResource(id = R.string.isnumero_vans_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }


            Spacer(modifier = Modifier.padding(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        val putVan = PutVan(
                            foto = urlImage,
                            id_modelo = idModelo,
                            id_motorista = idDriver.toString().toInt(),
                            quantidade_vagas = numeroVagasState,
                            placa = placaState,
                            status_van = 1
                        )

                        Log.i("ds3m", "EditarVan: $putVan")

                        val putVanCall =
                            GetFunctionsCall.getVanCall().putVan(idVan.toString(), putVan)

                        putVanCall.enqueue(object : Callback<String> {
                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>,
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        context, "Van atualizada com sucesso", Toast.LENGTH_SHORT
                                    ).show()
                                    context.startActivity(
                                        Intent(
                                            context, SuasVansActivity::class.java
                                        )
                                    )
                                }
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.i("ds3m", "onFailure: ${t.message}")
                            }
                        })

                    }, colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.save)
                    )
                }

            }
        }
    }

}