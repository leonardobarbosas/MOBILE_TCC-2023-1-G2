package br.senai.sp.jandira.vanbora.components.footer

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.activities.LocalizeActivity


@Composable
fun Footer () {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier.background(Color(224, 180, 65, 255)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.clickable {
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.AirportShuttle,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight(0.3f)
                )
                Text(
                    text = stringResource(id = R.string.drivers),
                    fontSize = 16.sp
                )
            }

            Column(
                modifier = Modifier.clickable {
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Contrast,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight(0.3f)
                )
                Text(
                    text = stringResource(id = R.string.contract),
                    fontSize = 16.sp
                )
            }

            Column(
                modifier = Modifier.clickable {
                    val intent = Intent(context, LocalizeActivity::class.java)
                    context.startActivity(intent)
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Map,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight(0.3f)
                )
                Text(
                    text = stringResource(id = R.string.localize),
                    fontSize = 16.sp
                )
            }
        }

    }
}