package br.senai.sp.jandira.vanbora.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.HeaderSelectDriverComplement
import br.senai.sp.jandira.vanbora.components.forms.userdriver.SelectUserDriver
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SelectActivity : ComponentActivity() {
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
                    Select()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Select() {

    val context = LocalContext.current

    val activityMain by remember {
        mutableStateOf(MainActivity::class.java)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background3),
                contentScale = ContentScale.Crop
            )
            .padding(top = 30.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Header - Logo do VanBora
        HeaderSelectDriverComplement(context = context, componentActivity = activityMain.newInstance())

        SelectUserDriver()
    }

}
