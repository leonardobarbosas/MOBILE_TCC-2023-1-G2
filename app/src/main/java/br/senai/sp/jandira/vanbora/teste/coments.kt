package br.senai.sp.jandira.vanbora.teste

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R

@Composable
fun comentarios () {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.1f)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Pedro Menezes",
                color = Color(202, 149, 13, 255),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Domingo, 12 out. 2022",
                fontSize = 10.sp
            )
        }
        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                    "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                    "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset" +
                    "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software" +
                    " like Aldus PageMaker including versions of Lorem Ipsum.",
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            fontSize = 8.sp
        )
    }
    
    Spacer(modifier = Modifier.padding(10.dp))
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.1f)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = "Pedro Menezes",
                color = Color(202, 149, 13, 255),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Domingo, 12 out. 2022",
                fontSize = 10.sp
            )
        }
        Text(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                    "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                    "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                    "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset" +
                    "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software" +
                    " like Aldus PageMaker including versions of Lorem Ipsum.",
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            fontSize = 8.sp
        )
    }

}