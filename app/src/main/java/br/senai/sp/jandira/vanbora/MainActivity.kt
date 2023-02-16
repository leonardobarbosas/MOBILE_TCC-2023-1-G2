package br.senai.sp.jandira.vanbora

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView()
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)

@Composable
fun LoginView() {

    var emailState by rememberSaveable() {
        mutableStateOf("")
    }

    var senhaState by rememberSaveable() {
        mutableStateOf("")
    }

    val emailFocusRequester = FocusRequester()


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Header - Logo do VanBora
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.vanbora),
                contentDescription = "vanbora logo",
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
            )
        }

        //Main - Parte dos dados para registro
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .paint(painter = painterResource(id = R.drawable.bola)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier )
            Text(
                text = stringResource(id = R.string.join_account),
                color = Color.Black,
                fontSize = 23.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            OutlinedTextField(
                value = emailState, onValueChange = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                    .focusRequester(emailFocusRequester),
                label = { Text(text = stringResource(id = R.string.email) )}
            )
        OutlinedTextField(
            value = senhaState, onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                .focusRequester(emailFocusRequester),
            label = { Text(text = stringResource(id = R.string.password)) }
        )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(id = R.string.my_password),
                    color = Color.Black,
                    fontSize = 10.sp,

                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Yellow)

                ) {
                    Text(
                        text = stringResource(id = R.string.join)
                    )
                }

            }

        }

        //Footer - Criar cotnat/loogin com o google
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_have_account),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(id = R.string.create_account),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

    }

