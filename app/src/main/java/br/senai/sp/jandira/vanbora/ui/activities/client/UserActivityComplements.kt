package br.senai.sp.jandira.vanbora.ui.activities.client

import android.os.Bundle
import android.util.Log
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
import br.senai.sp.jandira.vanbora.components.forms.user.UserInfos
import br.senai.sp.jandira.vanbora.ui.activities.global.SelectActivity
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class UserActivityComplements : ComponentActivity() {
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

                    DadosAdicionaisUser()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DadosAdicionaisUser() {

    val context = LocalContext.current

    val selectActivy by remember {
        mutableStateOf(SelectActivity::class.java)
    }

    val intent = (context as UserActivityComplements).intent

    val name = intent.getStringExtra("name").toString()
    val email = intent.getStringExtra("email").toString()
    val senha = intent.getStringExtra("senha").toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Header
        HeaderSelectDriverComplement(
            context = context,
            componentActivity = selectActivy.newInstance()
        )

        Log.i("ds3m", "DadosAdicionaisUser: $name, $email, $senha")

        //Main and Footer
        UserInfos(name, email, senha)
    }
}

