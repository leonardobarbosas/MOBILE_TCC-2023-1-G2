package br.senai.sp.jandira.vanbora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.components.HeaderCadastroLogin
import br.senai.sp.jandira.vanbora.components.ValidateLoginCadastroForm
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import br.senai.sp.jandira.vanbora.utils.ValidateLoginCadastroFooter
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

    val context = LocalContext.current

    val loginCadastroValidate by remember {
        mutableStateOf(false)
    }

    val loginCadastroValidateFooter by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        //Header - Logo do VanBora
        HeaderCadastroLogin()

        //Main - Parte dos dados para registro
        ValidateLoginCadastroForm(loginCadastro = loginCadastroValidate)

        //Footer - Criar cotnat/loogin com o google
        ValidateLoginCadastroFooter(loginCadastro = loginCadastroValidateFooter)

    }

}