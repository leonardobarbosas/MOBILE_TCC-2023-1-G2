package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.HeaderCadastroLogin
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.teste.comentarios
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class PerfilActivity : ComponentActivity() {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Perfil() {

    val context = LocalContext.current

    val localizeMain by remember {
        mutableStateOf(LocalizeActivity::class.java)
    }

    Column() {
        //Header
        HeaderSelectDriverComplement(
            context = context, componentActivity = localizeMain.newInstance()
        )

        //Main
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
            ) {

            }


            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(255, 237, 185, 255))
                        .fillMaxWidth()
                ) {

                    Text(text = "2 comentários", fontSize = 26.sp)

                    comentarios()

                }
            }
        }
    }
}



