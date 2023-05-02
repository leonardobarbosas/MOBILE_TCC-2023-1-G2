package br.senai.sp.jandira.vanbora.components.forms.userdriver

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.ui.activities.client.UserActivityComplements
import br.senai.sp.jandira.vanbora.ui.activities.driver.DriverActivityComplements

@Composable
fun SelectUserDriver(name: String, email: String, senha: String){

    val context = LocalContext.current

    val activityMain by remember {
        mutableStateOf(MainActivity::class.java)
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
                    val intent = Intent(context, DriverActivityComplements::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("email", email)
                    intent.putExtra("senha", senha)
                    context.startActivity(intent)
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
                onClick = {

                    val intent = Intent(context, UserActivityComplements::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("email", email)
                    intent.putExtra("senha", senha)
                    context.startActivity(intent)
                },
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