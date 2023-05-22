package br.senai.sp.jandira.vanbora.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.functions_click.LoginDriverClient
import br.senai.sp.jandira.vanbora.functions_click.LoginUserCLient
import br.senai.sp.jandira.vanbora.model.driver.Van

@Composable
fun FormMainLogin(){

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

    val checkedStateDriver = remember {
        mutableStateOf(false)
    }

    val checkedStateUser = remember {
        mutableStateOf(false)
    }

    val icon = if (senhaVisibility) {
        painterResource(id = R.drawable.visibility)
    } else {
        painterResource(id = R.drawable.visibilityoff)
    }

    val emailFocusRequester = FocusRequester()

    var context = LocalContext.current


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
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0, 0, 0, 255),
                unfocusedBorderColor = Color(0, 0, 0, 255)
            )
        )
        if(isEmailError){
            Text(
                text = stringResource(id = R.string.email_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }
        if(isEmailErrorArroba){
            Text(
                text = stringResource(id = R.string.arroba_error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
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
                    ),
                    textAlign = TextAlign.Center
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp),
                color = Color.Red,
                fontSize = 15.sp,
                textAlign = TextAlign.End
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 52.dp, end = 52.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedStateDriver.value,
                onCheckedChange = { checkedStateDriver.value = it },
                colors = CheckboxDefaults.colors(Color.Gray)
            )
            Text(text = stringResource(R.string.driver), fontSize = 10.sp)

            Spacer(modifier = Modifier.padding(16.dp))

            Checkbox(
                checked = checkedStateUser.value,
                onCheckedChange = { checkedStateUser.value = it },
                colors = CheckboxDefaults.colors(Color.Gray)
            )
            Text(text = stringResource(R.string.user), fontSize = 10.sp)
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

                    if (checkedStateDriver.value){
                        LoginDriverClient(emailProps = emailState,  senhaProps = senhaState, context = context)
                    }else if (checkedStateUser.value){
                        LoginUserCLient(emailProps = emailState,  senhaProps = senhaState, context = context)
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(250, 210, 69, 255))

            ) {
                Text(
                    text = stringResource(id = R.string.join)
                )
            }

        }

    }
}