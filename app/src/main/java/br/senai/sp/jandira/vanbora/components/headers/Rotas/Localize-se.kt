package br.senai.sp.jandira.vanbora.components.headers.Rotas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.components.footer.Footer
import br.senai.sp.jandira.vanbora.components.forms.localize.Localizese
import br.senai.sp.jandira.vanbora.components.headers.Header

@Composable
fun LocalizeSe () {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background2),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //Main
        Localizese()

        //Footer
        Footer()

    }
}