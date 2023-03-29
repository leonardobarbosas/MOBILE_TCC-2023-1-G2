package br.senai.sp.jandira.vanbora.components.headers.Rotas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.vanbora.components.headers.Destinos.*


@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Rota1.rota
    ){
        composable(Rota1.rota){
            VerPerfil()
        }
        composable(Rota2.rota){
            MeusContatos()
        }
        composable(Rota3.rota){
            LocalizeSe()
        }
        composable(Rota4.rota){
            ContateNos()
        }
    }
}
