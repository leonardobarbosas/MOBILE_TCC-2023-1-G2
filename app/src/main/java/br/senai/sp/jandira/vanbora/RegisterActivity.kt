package br.senai.sp.jandira.vanbora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme

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
                    Register()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Register() {

    var emailState by rememberSaveable() {
        mutableStateOf("")
    }

    var senhaState by rememberSaveable() {
        mutableStateOf("")
    }

    var nameState by rememberSaveable() {
        mutableStateOf("")
    }

    var isNameError by remember() {
        mutableStateOf(false)
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

    val senhaFocusRequester = FocusRequester()

    val context = LocalContext.current

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
                text = stringResource(id = R.string.create_account_register),
                color = Color.Black,
                fontSize = 23.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            OutlinedTextField(
                value = nameState, onValueChange = {
                    nameState = it

                    if (it == "" || it == null) {
                        isNameError
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                    .focusRequester(senhaFocusRequester),
                label = {
                    Text(
                        text = stringResource(id = R.string.name),
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )
                },
                trailingIcon = {
                    if (isNameError) Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = ""
                    )
                },
                isError = isNameError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0, 0, 0, 255),
                    unfocusedBorderColor = Color(0, 0, 0, 255)
                )
            )
            if(isNameError){
                Text(
                    text = stringResource(id = R.string.name_error),
                    modifier = Modifier.fillMaxWidth().padding(end = 52.dp),
                    color = Color.Red,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End
                )
            }
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
                    .focusRequester(senhaFocusRequester),
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
            OutlinedTextField(
                value = senhaState, onValueChange = {
                    senhaState = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 52.dp, end = 52.dp)
                    .focusRequester(senhaFocusRequester),
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

                Button(
                    onClick = {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

                ) {
                    Text(
                        text = stringResource(id = R.string.google)
                    )
                }

                Button(
                    onClick = {
                        isNameError = nameState.length == 0
                        isEmailError = emailState.length == 0
                        isSenhaError = senhaState.length == 0
                    },
                    colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

                ) {
                    Text(
                        text = stringResource(id = R.string.next)
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.have_account),
                    modifier = Modifier.clickable { context.startActivity(Intent(context, MainActivity::class.java)) },
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = stringResource(id = R.string.join_account),
                    modifier = Modifier.clickable { context.startActivity(Intent(context, MainActivity::class.java)) },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }

}