package br.senai.sp.jandira.vanbora.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.vanbora.R

@Composable
fun HeaderSelectDriverComplement(context: Context, componentActivity: ComponentActivity){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { context.startActivity(Intent(context, componentActivity::class.java)) }
            )
            Image(
                painter = painterResource(id = R.drawable.vanbora),
                contentDescription = "vanbora logo",
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp),
            )
        }
    }
}