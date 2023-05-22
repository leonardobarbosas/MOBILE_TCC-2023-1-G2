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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewDriver
import br.senai.sp.jandira.vanbora.model.driver.Driver
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
    var modeloVan by rememberSaveable {
        mutableStateOf("")
    }
    var vagasVan by rememberSaveable {
        mutableStateOf("")
    }
    var precoVan by rememberSaveable {
        mutableStateOf("")
    }

    var isPlacaVanError by rememberSaveable {
        mutableStateOf(false)
    }
    var isModeloVanError by rememberSaveable {
        mutableStateOf(false)
    }
    var isVagasVanError by rememberSaveable {
        mutableStateOf(false)
    }
    var isPrecoVanError by rememberSaveable {
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
            value = modeloVan,
            onValueChange = {
                modeloVan = it
                if (it == "" || it == null) {
                    isModeloVanError
                }
            },
            modifier = Modifier.padding(bottom = 20.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.modelo_van)
                )
            },
            trailingIcon = {
                if (isModeloVanError) Icon(
                    imageVector = Icons.Default.Warning, contentDescription = ""
                )
            },
            isError = isModeloVanError,
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

        OutlinedTextField(
            value = precoVan,
            onValueChange = {
                precoVan = it
                if (it == "" || it == null) {
                    isPrecoVanError
                }
            },
            label = {
                Text(
                    text = stringResource(id = R.string.preco_van)
                )
            },
            trailingIcon = {
                if (isPrecoVanError) Icon(
                    imageVector = Icons.Default.Warning, contentDescription = ""
                )
            },
            isError = isPrecoVanError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }


    //BUTTON
    Column() {
        Button(
            onClick = {
                isPlacaVanError = placaVan.length == 0
                isModeloVanError = modeloVan.length == 0
                isVagasVanError = vagasVan.length == 0
                isPrecoVanError = precoVan.length == 0


                val driverCallSave = GetFunctionsCall.getDriverCall().saveDriver(driver = driver)

                driverCallSave.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {

                            val getDriverByCpf =
                                GetFunctionsCall.getDriverCall().getDriverIdByCpf(driver.cpf)

                            getDriverByCpf.enqueue(object : Callback<Int> {
                                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                    if (response.isSuccessful) {

                                        val driverGet = GetFunctionsCall.getDriverCall()
                                            .getDriverById(response.body().toString())

                                        driverGet.enqueue(object : Callback<Driver> {
                                            override fun onResponse(
                                                call: Call<Driver>,
                                                response: Response<Driver>,
                                            ) {
                                                if (response.isSuccessful) {
                                                    var postVan = PostPutVan(
                                                        foto = "",
                                                        id_modelo = 0,
                                                        id_motorista = response.body()!!.id,
                                                        placa = placaVan,
                                                        quantidade_vagas = vagasVan,
                                                        status_van = 1
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
                                                Log.i("ds3m", "onFailure: ${t.message}")
                                            }
                                        })

                                    }
                                }

                                override fun onFailure(call: Call<Int>, t: Throwable) {
                                    Log.i("ds3m", "onFailure: ${t.message}")
                                }
                            })
                        }

                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.i("ds3m", "onFailure: ${t.message.toString()}")
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