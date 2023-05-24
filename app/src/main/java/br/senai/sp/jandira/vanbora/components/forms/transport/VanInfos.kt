import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewDriver
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.IdMotorista
import br.senai.sp.jandira.vanbora.model.driver.ModeloList
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.model.van.PostPutVan
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun VanInfos(
    driver: DriverPost,
) {

    var placaVan by rememberSaveable {
        mutableStateOf("")
    }
    var vagasVan by rememberSaveable {
        mutableStateOf("")
    }

    var isPlacaVanError by rememberSaveable {
        mutableStateOf(false)
    }
    var isVagasVanError by rememberSaveable {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()


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
                storage.reference.child("vans-profile-picture/${imageName.toString()}")

            val uploadTask = mountainsRef.putFile(selectedImage!!)

            uploadTask.addOnSuccessListener {
                mountainsRef.downloadUrl.addOnSuccessListener { url ->
                    urlImage = url.toString()
                }
            }

        })


    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    val modeloCall = GetFunctionsCall.getVanCall().getAllModels()

    var modeloList by remember {
        mutableStateOf(ModeloList(listOf()))
    }

    var idModelo by remember {
        mutableStateOf(0)
    }
    var modeloState by remember {
        mutableStateOf("")
    }


    if (succesImg == 1) {
        imageIcon = painterResource(id = R.drawable.baseline_linked_camera_24_back)
    } else if (succesImg == 2) {
        imageIcon = rememberAsyncImagePainter(model = selectedImage)
    }


    //IMAGE
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .padding(start = 80.dp, end = 80.dp)
            .background(Color(156, 156, 156, 0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(painter = imageIcon!!, contentDescription = "", modifier = Modifier
            .clickable {
                gallerLauncher.launch("image/*")
            }
            .size(200.dp)
        )
    }

    //OUTLINED
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        OutlinedTextField(
            value = placaVan,
            onValueChange = {
                placaVan = it
                if (it == "" || it == null) {
                    isPlacaVanError
                }
            },
            modifier = Modifier.padding(bottom = 20.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.placa_van)
                )
            },
            trailingIcon = {
                if (isPlacaVanError) Icon(
                    imageVector = Icons.Default.Warning, contentDescription = ""
                )
            },
            isError = isPlacaVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedTextField(
            value = vagasVan,
            onValueChange = {
                vagasVan = it
                if (it == "" || it == null) {
                    isVagasVanError
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.numero_vagas)
                )
            },
            trailingIcon = {
                if (isVagasVanError) Icon(
                    imageVector = Icons.Default.Warning, contentDescription = ""
                )
            },
            isError = isVagasVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Column() {


            modeloCall.enqueue(object : Callback<ModeloList> {
                override fun onResponse(call: Call<ModeloList>, response: Response<ModeloList>) {
                    if (response.isSuccessful) {
                        modeloList = response.body()!!
                    }
                }

                override fun onFailure(call: Call<ModeloList>, t: Throwable) {
                    Log.i("ds3m", "onFailure: ${t.message}")
                }
            })

            val icon = if (isMenuExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            OutlinedTextField(
                value = modeloState, onValueChange = {
                    modeloState = it

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
                modeloList.models.forEach {
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
    }


    //BUTTON
    Column() {
        Button(
            onClick = {
                isPlacaVanError = placaVan.length == 0
                isVagasVanError = vagasVan.length == 0


                val driverCallSave = GetFunctionsCall.getDriverCall().saveDriver(driver = driver)

                driverCallSave.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {

                            val getDriverByCpf =
                                GetFunctionsCall.getDriverCall().getDriverIdByCpf(driver.cpf)

                            getDriverByCpf.enqueue(object : Callback<IdMotorista> {
                                override fun onResponse(call: Call<IdMotorista>, response: Response<IdMotorista>) {
                                    if (response.isSuccessful) {

                                        val driverGet = GetFunctionsCall.getDriverCall()
                                            .getDriverById(response.body()!!.id.toString())

                                        driverGet.enqueue(object : Callback<Driver> {
                                            override fun onResponse(
                                                call: Call<Driver>,
                                                response: Response<Driver>,
                                            ) {
                                                if (response.isSuccessful) {
                                                    var postVan = PostPutVan(
                                                        foto = urlImage,
                                                        id_modelo = idModelo,
                                                        id_motorista = response.body()!!.id,
                                                        placa = placaVan,
                                                        quantidade_vagas = vagasVan
                                                    )
                                                    RegisterNewDriver(
                                                        postVan = postVan, context = context
                                                    )

                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<Driver>,
                                                t: Throwable,
                                            ) {
                                                Log.i("ds3m", "onFailure: ${t.message} motorista")
                                            }
                                        })

                                    }
                                }

                                override fun onFailure(call: Call<IdMotorista>, t: Throwable) {
                                    Log.i("ds3m", "onFailure: ${t.message} post")
                                }
                            })
                        }

                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.i("ds3m", "onFailure: ${t.message.toString()} ")
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