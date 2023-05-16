package br.senai.sp.jandira.vanbora.components.driver.footer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.vanbora.components.confirm.MainViewModel
import br.senai.sp.jandira.vanbora.components.headers.Rotas.*
import br.senai.sp.jandira.vanbora.components.headers.RotasDriver.Notificacoes
import br.senai.sp.jandira.vanbora.components.headers.RotasDriver.SeusContratos
import br.senai.sp.jandira.vanbora.components.headers.RotasDriver.SuasEscolas
import br.senai.sp.jandira.vanbora.ui.activities.driver.Vans


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun FooterDriver() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        DestinosDriver.RotaSuasVans,
        DestinosDriver.RotaSuasEscolas,
        DestinosDriver.RotaNotificacoes,
        DestinosDriver.RotaSeusContratos,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: DestinosDriver,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title, color = Color.Black)
        },
        icon = {
            Icon(
                imageVector = screen.ico,
                contentDescription = "Navigation Icon",
                tint = Color.Black
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier.background(Color(224, 180, 65, 255))
    )
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = DestinosDriver.RotaSuasVans.route) {



        composable(DestinosDriver.RotaNotificacoes.route) {
            Notificacoes(viewModel = MainViewModel())
        }

        composable(DestinosDriver.RotaSeusContratos.route) {
            SeusContratos()
        }

        composable(DestinosDriver.RotaSuasEscolas.route) {
            SuasEscolas()
        }

        composable(DestinosDriver.RotaSuasVans.route) {
            Vans()
        }


    }
}