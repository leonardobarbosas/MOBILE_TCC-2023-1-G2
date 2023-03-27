package br.senai.sp.jandira.vanbora.components.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Header() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Menu Burguer
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(0.11f)
                    .clickable {

                    }
            )
            //Logo
            Logo()

            //Perfil
            Icon(
                imageVector = Icons.Filled.SupervisedUserCircle,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.1f)
            )
        }
    }
}
