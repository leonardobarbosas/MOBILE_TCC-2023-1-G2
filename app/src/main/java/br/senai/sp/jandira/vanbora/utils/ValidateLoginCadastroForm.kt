package br.senai.sp.jandira.vanbora.components

import androidx.compose.runtime.Composable

@Composable
fun ValidateLoginCadastroForm(loginCadastro: Boolean){

    if(loginCadastro){
        FormMainLogin()
    }else{
        FormMainCadastro()
    }

}