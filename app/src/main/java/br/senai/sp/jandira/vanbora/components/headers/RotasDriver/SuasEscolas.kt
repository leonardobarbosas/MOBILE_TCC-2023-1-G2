package br.senai.sp.jandira.vanbora.components.headers.RotasDriver

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderMotorista

@Composable
fun SuasEscolas() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            )
    ) {

        HeaderMotorista()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.suas_vans_suas),
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#E0B441")),
                    shadow = Shadow(color = androidx.compose.ui.graphics.Color.Black, offset = Offset(0F, 4F), blurRadius = 5f)
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Escolas",
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    shadow = Shadow(color = androidx.compose.ui.graphics.Color.Black, offset = Offset(0F, 4F), blurRadius = 5f)
                )
            )
        }

    }
}