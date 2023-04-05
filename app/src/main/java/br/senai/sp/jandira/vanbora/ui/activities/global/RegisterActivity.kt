package br.senai.sp.jandira.vanbora.ui.activities.global

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.FooterCadastro
import br.senai.sp.jandira.vanbora.components.FormMainCadastro
import br.senai.sp.jandira.vanbora.components.HeaderCadastroLogin
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class RegisterActivity : ComponentActivity() {
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
                    Register()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Register() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Header - Logo do VanBora
        HeaderCadastroLogin()

        //Main - Parte dos dados para registro
        FormMainCadastro()

        //Footer - Criar cotnat/loogin com o google
        FooterCadastro()

    }

}