package br.senai.sp.jandira.vanbora.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.model.user.UserModel
import br.senai.sp.jandira.vanbora.ui.activities.ui.theme.VanboraTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class GetAllUsersActivity : ComponentActivity() {
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

                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var users by remember {
        mutableStateOf(listOf<UserModel>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ){
            items(users){
                Card() {
                    Text(text = "alo")
                    Text(text = "alo")
                    Text(text = "alo")
                    Text(text = "alo")
                    Text(text = "alo")

                }
            }
        }


        
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//
//            items(users) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    backgroundColor = Color(204, 149, 8, 255)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.addphoto),
//                        contentDescription = "",
//                        modifier = Modifier.fillMaxSize()
//                    )
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceAround
//                    ) {
//                        Column() {
//                            Text(text = it.foto, color = Color.Red)
//                        }
//                        Column() {
//                            Text(text = it.nome, color = Color.White)
//                            Text(text = it.telefone, color = Color.White)
//                            Text(text = it.rg, color = Color.White)
//                        }
//                    }
//                }
//            }
//
//        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    VanboraTheme {
        Greeting("Android")
    }
}