package br.senai.sp.jandira.vanbora.ui.activities.client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.HeaderPerfil
import br.senai.sp.jandira.vanbora.functions_click.RegisterNewComment
import br.senai.sp.jandira.vanbora.model.comment.Comment
import br.senai.sp.jandira.vanbora.model.comment.CommentX
import br.senai.sp.jandira.vanbora.model.contract.EscolaDriver
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MotoristaPerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val systemUi = rememberSystemUiController()
                    SideEffect {
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0), darkIcons = true
                        )
                    }
                    Perfil()
                }
            }
        }
    }
}

@Composable
fun Perfil() {

    var expandState by remember {
        mutableStateOf(false)
    }

    var commentState by remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val intent = (context as MotoristaPerfilActivity).intent

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
            Log.i("ds3m", "onFailure call driver: $t")
        }
    })


    val idUser = intent.getStringExtra("id_usuario")
    val userCall = GetFunctionsCall.getUserCall().getUserById(id = idUser.toString())
    var user by remember {
        mutableStateOf<User?>(null)
    }
    userCall.enqueue(object : Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            user = response.body()!!
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure $t")
        }
    })

    val commentCall = GetFunctionsCall.getCommentCall().getCommentByDriver(id = idDriver.toString())
    var comment by remember {
        mutableStateOf(Comment(listOf()))
    }
    Log.i("ds3m", "Perfil: $comment")
    commentCall.enqueue(object : Callback<Comment> {
        override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
            comment = response.body()!!
            Log.i("ds3m", "onResponse: $comment")
        }

        override fun onFailure(call: Call<Comment>, t: Throwable) {
            Log.i("ds3m", "onFailure escolas: ${t.message}")
        }
    })

    var success by remember {
        mutableStateOf(0)
    }

    Column {
        //Header

        HeaderPerfil()

        //Main
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(252, 241, 211, 255))
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .background(Color.Black, shape = RoundedCornerShape(25.dp))
                    .height(200.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = rememberAsyncImagePainter(driver?.van?.get(0)?.foto),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(30.dp))
                        .background(Color.Black, shape = RoundedCornerShape(25.dp))
                        .border(BorderStroke(2.dp, SolidColor(Color.Black)))
                )
                Image(
                    painter = rememberAsyncImagePainter(driver?.foto),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
            }

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = "${driver?.nome}",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {

                        val intentSelect = Intent(context, EnviarContratoActivity::class.java)

                        intentSelect.putExtra("id_motorista", idDriver)
                        intentSelect.putExtra("id_usuario", idUser)

                        context.startActivity(intentSelect)
                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.contrac)
                    )
                }
                Avaliacao()
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${driver?.van?.get(0)?.quantidade_vagas}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    if (driver?.van?.get(0)?.quantidade_vagas == 1) {
                        Text(
                            text = "Vaga",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Vagas",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }


                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Desde",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "${driver?.inicio_carreira}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "no transporte",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${driver?.descricao}", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.padding(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Whatsapp,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(5, 172, 27, 255)
                    )
                    Text(
                        text = "${driver?.telefone}",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(0, 133, 255, 255)
                    )
                    Text(
                        text = "${driver?.telefone}",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color(0, 0, 0, 255)
                    )
                    Text(text = "${driver?.email}", fontSize = 14.sp, textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                onClick = {

                    expandState = true

                },
                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
            ) {
                Text(
                    text = stringResource(id = R.string.comments)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))

            //Footer
            AnimatedVisibility(
                visible = expandState,
                enter = slideIn(tween(durationMillis = 700)) {
                    IntOffset(0, it.height)
                },
                exit = slideOut(tween(durationMillis = 700)) {
                    IntOffset(0, it.height)
                }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight(1f)
                        .padding(top = 40.dp),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    backgroundColor = Color(255, 237, 185, 255),

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Image(
                            imageVector = Icons.Filled.ArrowCircleUp,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                expandState = false
                            }
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = commentState,
                                onValueChange = {
                                    commentState = it
                                },
                                modifier = Modifier.width(200.dp),
                                label = {
                                    Text(text = "Insira um comentário")
                                },
                                singleLine = true,
                                maxLines = 1
                            )

                            Button(
                                onClick = {
                                    if (commentState == ""){
                                        Toast.makeText(context, "Campo não prenchido", Toast.LENGTH_SHORT).show()
                                    }else{
                                        RegisterNewComment(
                                            comentario = commentState,
                                            motorista = idDriver.toString().toInt(),
                                            usuario = idUser.toString().toInt()
                                        )

                                        Toast.makeText(
                                            context,
                                            "Commentário enviado com sucesso!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        simulateHotReload(context)
                                    }

                                },
                                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))
                            ) {
                                Text(text = "Enviar")
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(top = 16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(comment.comentarios) { comments ->


                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 8.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp, end = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                                Image(
                                                    painter = rememberAsyncImagePainter(comments.user.foto),
                                                    contentDescription = "user",
                                                    modifier = Modifier
                                                        .size(34.dp)
                                                        .clip(CircleShape)
                                                        .border(1.dp, Color.Gray, CircleShape),
                                                    contentScale = ContentScale.Crop,

                                                    )

                                                Spacer(modifier = Modifier.padding(3.dp))

                                                Text(
                                                    text = comments.user.nome,
                                                    fontSize = 20.sp,
                                                    color = Color(202, 149, 13, 255)
                                                )

                                            Spacer(modifier = Modifier.padding(10.dp))


                                            if (idUser.toString() == comments.user.id.toString()) {
                                                    Button(
                                                        onClick = {
                                                            val comment = CommentX(
                                                                comments.comentario,
                                                                comments.id,
                                                                comments.id_motorista,
                                                                comments.user
                                                            )

                                                            val callCommentDelete = GetFunctionsCall.getCommentCall().deleteComment(comment.id)
                                                            callCommentDelete.enqueue(object: Callback<String> {
                                                                override fun onResponse(
                                                                    call: Call<String>, response: Response<String>
                                                                ) {
                                                                    Toast.makeText(context, "Commentário excluído com sucesso", Toast.LENGTH_SHORT).show()
                                                                    simulateHotReload(context)

                                                                }
                                                                override fun onFailure(call: Call<String>, t: Throwable) {
                                                                    Log.i("ds3m", "fali")
                                                                }

                                                            })


                                                        },
                                                        shape = CircleShape,
                                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                                    ) {
                                                        Image(
                                                            imageVector = Icons.Filled.Delete,
                                                            contentDescription = "",
                                                            modifier = Modifier
                                                                .clip(CircleShape)
                                                                .height(16.dp)
                                                        )
                                                    }
                                                }

                                            }
                                        }

                                        Text(
                                            text = comments.comentario,
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.ExtraLight
                                        )


                                    }

                            }
                        }



                    }
                }
            }


        }
    }


@Composable
fun Avaliacao() {

    val context = LocalContext.current

    val intent = (context as MotoristaPerfilActivity).intent

    val idDriver = intent.getStringExtra("id_motorista")

    val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())

    var driver by remember {
        mutableStateOf<Driver?>(null)
    }
    var testeDriver by remember {
        mutableStateOf(0)
    }

    driverCall.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {


            if (response.body()!! != null) {
                testeDriver = 1

                driver = response.body()!!
            }
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure call driver if: $t")
        }
    })

    if (testeDriver == 1) {
        if (driver?.avaliacao!! == 10) {
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 9) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 8) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 7) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 6) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 5) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 4) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 3) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else if (driver?.avaliacao!! == 2) {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        } else {
            Row() {
                Icon(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = "",
                    tint = Color(238, 179, 31, 255)
                )
            }
        }
    }
}



