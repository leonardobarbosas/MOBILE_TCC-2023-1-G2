package br.senai.sp.jandira.vanbora.components.headers

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.components.HeaderCadastroLogin


@Composable
fun HeaderSelect() {

    var context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(end = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {

                        val intentSelect = Intent(context, MainActivity::class.java)
                        context.startActivity(intentSelect)

                    }
            )

            HeaderCadastroLogin()
        }
    }
}