package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.components.forms.contract.EnviarContrato
import br.senai.sp.jandira.vanbora.components.headers.header.HeaderEnviarContrato
import br.senai.sp.jandira.vanbora.ui.activities.client.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class EnviarContratoActivity : ComponentActivity() {
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
                        systemUi.setStatusBarColor(
                            color = Color(255, 255, 255, 0),
                            darkIcons = true
                        )
                    }
                    EnvioDeContrato()

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnvioDeContrato() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        HeaderEnviarContrato()

        EnviarContrato()

    }
}

