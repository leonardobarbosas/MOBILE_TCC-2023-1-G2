package br.senai.sp.jandira.vanbora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.theme.VanboraTheme

class SelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanboraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Select()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Select() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background3),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        //Header - Logo do VanBora
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
                        .clickable { context.startActivity(Intent(context, MainActivity::class.java)) }
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

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.select),
                color = Color(250, 210, 69, 255),
                fontSize = 40.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.one_option),
                color = Color.Black,
                fontSize = 40.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.para),
                color = Color.Black,
                fontSize = 40.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.registrar),
                color = Color(250, 210, 69, 255),
                fontSize = 40.sp,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Button(
                    onClick = {
                        context.startActivity(Intent(context, DriverActivityComplements::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255))
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.driver),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(id = R.string.driver)
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255))
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.usuario),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(id = R.string.user)
                    )
                }
            }
        }
    }

}
