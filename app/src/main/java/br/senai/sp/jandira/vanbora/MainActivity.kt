package br.senai.sp.jandira.vanbora

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.senai.sp.jandira.vanbora.R

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


@Preview
@Composable
fun LoginView() {

    var emailState by rememberSaveable() {
        mutableStateOf("")
    }

    var senhaState by rememberSaveable() {
        mutableStateOf("")

    }

    var isEmailError by remember() {
        mutableStateOf(false)

    }

    var isEmailErrorArroba by remember() {
        mutableStateOf(false)

    }

    var isSenhaError by remember() {
        mutableStateOf(false)
    }

    var senhaVisibility by remember() {
        mutableStateOf(false)
    }

    val icon = if (senhaVisibility) {
        painterResource(id = R.drawable.visibility)
    } else {
        painterResource(id = R.drawable.visibilityoff)
    }

    val emailFocusRequester = FocusRequester()

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceAround
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
                    .width(300.dp)
                    .height(100.dp),
            )
        }

        //Main - Parte dos dados para registro
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.join_your_account),
                color = Color.Black,
                fontSize = 23.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            OutlinedTextField(
                value = emailState, onValueChange = {
                    emailState = it

                    if (it == "" || it == null) {
                        isEmailError
                    }
                    else if (it !== "@"){
                        isEmailErrorArroba
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                    .focusRequester(emailFocusRequester),
                label = {
                    Text(
                        text = stringResource(id = R.string.email),
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    if (isEmailError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isEmailError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if(isEmailError){
                Text(
                    text = stringResource(id = R.string.email_error),
                    modifier = Modifier.fillMaxWidth().padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }
            if(isEmailErrorArroba){
                Text(
                    text = stringResource(id = R.string.arroba_error),
                    modifier = Modifier.fillMaxWidth().padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }


            OutlinedTextField(
                value = senhaState, onValueChange = {
                    senhaState = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                    .focusRequester(emailFocusRequester),
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        fontSize = 15.sp,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        senhaVisibility = !senhaVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                isError = isSenhaError,
                visualTransformation = if (senhaVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if(isSenhaError){
                Text(
                    text = stringResource(id = R.string.senha_error),
                    modifier = Modifier.fillMaxWidth().padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.my_password),
                    color = Color.Black,
                    fontSize = 10.sp,

                    )
                Button(
                    onClick = {
                        isEmailError = emailState.length == 0
                        isSenhaError = senhaState.length == 0
                        context.startActivity(Intent(context, SelectActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

            ) {
                Text(
                    text = stringResource(id = R.string.google)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_have_account),
                    modifier = Modifier.clickable { context.startActivity(Intent(context, RegisterActivity::class.java)) },
                    fontSize = 12.sp,
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(id = R.string.create_account),
                    modifier = Modifier.clickable { context.startActivity(Intent(context, RegisterActivity::class.java)) },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}

