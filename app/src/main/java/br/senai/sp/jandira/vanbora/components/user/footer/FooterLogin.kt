package br.senai.sp.jandira.vanbora.components

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.activities.global.RegisterActivity

@Composable
fun FooterLogin(){

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_have_account),
                modifier = Modifier.clickable { context.startActivity(Intent(context, RegisterActivity::class.java)) },
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = stringResource(id = R.string.create_account),
                modifier = Modifier.clickable { context.startActivity(Intent(context, RegisterActivity::class.java)) },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}