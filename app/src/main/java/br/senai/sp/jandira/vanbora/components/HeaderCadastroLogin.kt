package br.senai.sp.jandira.vanbora.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R


@Preview(showBackground = true)
@Composable
fun HeaderCadastroLogin(){

    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.1f), horizontalArrangement = Arrangement.Center
    )
    {
        Column(
            modifier = Modifier
                .size(34.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(
                imageVector =  Icons.Filled.AirportShuttle,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(
                        color = Color(249, 171, 3, 255)
                    )
                    .padding(paddingValues = PaddingValues(5.dp)))

        }
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.app_name), textAlign = TextAlign.Center)
        }
    }
}
