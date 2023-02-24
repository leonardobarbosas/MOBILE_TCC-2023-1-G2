package br.senai.sp.jandira.vanbora.utils

import androidx.compose.runtime.Composable
import br.senai.sp.jandira.vanbora.components.FooterCadastro
import br.senai.sp.jandira.vanbora.components.FooterLogin

@Composable
fun ValidateLoginCadastroFooter(loginCadastro: Boolean){

    if (loginCadastro){
        FooterLogin()
    }else{
        FooterCadastro()
    }

}