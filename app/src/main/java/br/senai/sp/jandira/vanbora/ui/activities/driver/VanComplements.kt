package br.senai.sp.jandira.vanbora.ui.activities.driver

import VanInfos
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.headers.HeaderComplements
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class VanComplements : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUi = rememberSystemUiController()
                    SideEffect {
                        systemUi.setStatusBarColor(color = Color(255, 255, 255, 0), darkIcons = true)
                    }
                    VanInfosActivity()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VanInfosActivity(){

    val context = LocalContext.current

    val intent = (context as DriverActivityComplements).intent

    val name = intent.getStringExtra("name").toString()
    val email = intent.getStringExtra("email").toString()
    val senha = intent.getStringExtra("senha").toString()
    val rg = intent.getStringExtra("rg").toString()
    val cpf = intent.getStringExtra("cpf").toString()
    val cnh = intent.getStringExtra("cnh").toString()
    val descricao = intent.getStringExtra("descricao").toString()
    val telefone = intent.getStringExtra("telefone").toString()
    val inicio_carreira = intent.getStringExtra("inicio_carreira").toString()
    val data_nascimento = intent.getStringExtra("data_nascimento").toString()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),

    ) {

        //Header
        Column() {
            HeaderComplements()
        }
        //DIV PAI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            VanInfos(name, email, senha, rg, cpf, cnh, descricao, telefone, inicio_carreira, data_nascimento)

        }
    }
}
