package br.senai.sp.jandira.vanbora.components.headers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.vanbora.components.headers.Rotas.NavigationHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Header() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Destinos.Rota1,
        Destinos.Rota2,
        Destinos.Rota3,
        Destinos.Rota4
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        drawerContent = { Drawer(
            scope,
            scaffoldState,
            navController,
            menu_items = navigationItems)
        }
    ) {
        NavigationHost(navController)
    }

}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {

    TopAppBar(
        title = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                //Logo
                Logo()

                Icon(
                    imageVector = Icons.Filled.SupervisedUserCircle,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight(),
                    tint = Color.Black
                )
            }


        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(0.8f),
                    tint = Color.Black
                )
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.White
    )

}

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menu_items: List<Destinos>
) {

    Column {
        Card(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(Color(224, 180, 65, 255))
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(0.5f)
                )
                Column() {
                    Text(text = "Jessica Shmitz")
                    Text(text = "jessica@gmail.com")
                }
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        menu_items.forEach { item ->
            DrawerItem(item = item){
                navController.navigate(item.rota){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }

}

@Composable
fun DrawerItem(item: Destinos,
    onItemClick: (Destinos)->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .padding(8.dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Image(
            painterResource(id = item.ico),
            contentDescription = item.title
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = item.title)
    }
}