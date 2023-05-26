package br.senai.sp.jandira.vanbora.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import br.senai.sp.jandira.vanbora.model.DataStore.DataStoreAppData
import br.senai.sp.jandira.vanbora.model.dto.AuthDTO
import br.senai.sp.jandira.vanbora.model.dto.LoginResponse
import br.senai.sp.jandira.vanbora.ui.activities.global.SelectActivity
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FormMainLogin() {

    var token by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

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

    val dataStore = DataStoreAppData(context)
    val scope = rememberCoroutineScope()


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
                } else if (it !== "@") {
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
        if (isEmailError) {
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
        if (isEmailErrorArroba) {
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
        if (isSenhaError) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 52.dp, end = 52.dp),
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
                    if (token.isNotEmpty()) {
                        scope.launch {
                            dataStore.deleteToken()
                        }
                    }
                    val authDTO = AuthDTO(emailState, senhaState)
                    val call = RetrofitApi.retrofitServiceAuth().login(authDTO)

                    call.enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>, response: Response<LoginResponse>
                        ) {
                            if (response.isSuccessful) {
                                val jwt = JWT(response.body()!!.token)

                                scope.launch {
                                    dataStore.saveToken(response.body()!!.token)
                                    dataStore.saveIdUser(jwt.getClaim("id").asString().toString())
                                    dataStore.saveType(jwt.getClaim("type").asString().toString())
                                    dataStore.saveName(response.body()!!.data.nome)
                                    dataStore.saveEmailRegister(response.body()!!.data.email)
                                    dataStore.savePasswordRegister(response.body()!!.data.senha)
                                }

                                if (response.body()?.token?.isNotEmpty() == true) {
                                    val intent = Intent(
                                        context,
                                        SelectActivity::class.java
                                    ).putExtra("token", response.body()!!.token)
                                    startActivity(context, intent, null)
                                } else {
                                    Toast.makeText(context, "Usuário ou senha incorretos! Tente novamente.", Toast.LENGTH_SHORT).show()
                                }

                            }else {
                                Toast.makeText(context, "Algum dado está incorreto", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(context, "Tente mais tarde", Toast.LENGTH_SHORT).show()
                        }

                    })

//                    isEmailError = emailState.length == 0
//                    isSenhaError = senhaState.length == 0
//
//                    if (checkedStateDriver.value){
//                        LoginDriverClient(emailProps = emailState,  senhaProps = senhaState, context = context)
//                    }else if (checkedStateUser.value){
//                        LoginUserCLient(emailProps = emailState,  senhaProps = senhaState, context = context)
//                    }
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