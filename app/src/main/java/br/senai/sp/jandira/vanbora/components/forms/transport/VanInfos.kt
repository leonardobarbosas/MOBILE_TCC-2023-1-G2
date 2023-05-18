import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewDriver
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.user.User

@Composable
fun VanInfos(
    email: String,
    senha: String,
    rg: String,
    cpf: String,
    descricao: String,
    cnh: String,
    telefone: String,
    inicio_carreira: String,
    name: String,
    data_nascimento: String,
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


    //image
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var context = LocalContext.current

    var bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    //IMAGE
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
            .padding(start = 80.dp, end = 80.dp)
            .background(Color(156, 156, 156, 0))
            .clickable {
                launcher.launch("image/*")
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Icon(
            imageVector = Icons.Filled.PhotoCamera,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
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
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
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
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
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
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
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
                    imageVector = Icons.Default.Warning,
                    contentDescription = ""
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

                RegisterNewDriver(
                    senha = senha,
                    cnh = cnh, cpf = cpf,
                    data_nascimento = data_nascimento,
                    descricao = descricao,
                    email = email,
                    foto = "url_foto",
                    inicio_carreira = inicio_carreira,
                    nome = name,
                    rg = rg,
                    telefone = telefone,
                    context = context
                )

            },
            colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

        ) {
            Text(
                text = stringResource(id = R.string.save)
            )
        }
    }
}