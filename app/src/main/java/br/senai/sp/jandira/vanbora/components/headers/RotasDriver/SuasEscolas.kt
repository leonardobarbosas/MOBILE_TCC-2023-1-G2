package br.senai.sp.jandira.vanbora.components.headers.RotasDriver

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vanbora.R
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.components.headers.headerDriver.HeaderMotorista
import br.senai.sp.jandira.vanbora.functions_click.RegiterNewSchool
import br.senai.sp.jandira.vanbora.model.contract.EscolaDriver
import br.senai.sp.jandira.vanbora.model.contract.EscolaList
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import br.senai.sp.jandira.vanbora.model.contract.SchoolPost
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        var saveState by rememberSaveable() {
            mutableStateOf("")
        }

        var isSaveError by remember() {
            mutableStateOf(false)

        }

        val context = LocalContext.current

        val intent = (context as SuasVansActivity).intent


        var driver by remember {
            mutableStateOf<Driver?>(null)
        }
        val idDriver = intent.getStringExtra("id")

        val driverCall = GetFunctionsCall.getDriverCall().getDriverById(id = idDriver.toString())
        driverCall.enqueue(object : Callback<Driver> {
            override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
                if(response.isSuccessful){
                    driver = response.body()!!
                }else{
                    driver = Driver()
                }
            }

            override fun onFailure(call: Call<Driver>, t: Throwable) {
                Log.i("ds3m", "onFailure driver")
            }
        })


        val escolaCall =
            GetFunctionsCall.getEscolaCall().getSchoolByDriver(id = idDriver.toString())
        var escolas by remember {
            mutableStateOf(EscolaDriver(listOf()))
        }
        escolaCall.enqueue(object : Callback<EscolaDriver> {
            override fun onResponse(call: Call<EscolaDriver>, response: Response<EscolaDriver>) {
                escolas = response.body()!!
            }

            override fun onFailure(call: Call<EscolaDriver>, t: Throwable) {
                Log.i("ds3m", "onFailure escolas: ${t.message}")
            }
        })

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
                    shadow = Shadow(
                        color = androidx.compose.ui.graphics.Color.Black,
                        offset = Offset(0F, 4F),
                        blurRadius = 5f
                    )
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Escolas",
                fontSize = 45.sp,
                style = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color(android.graphics.Color.parseColor("#FFFFFF")),
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(0F, 4F),
                        blurRadius = 5f
                    )
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
                    .padding(10.dp)
                    .background(Color(255, 248, 228, 255)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (escolas.schools.isEmpty()) {
                    Toast.makeText(context, "Nenhuma escola cadastrada", Toast.LENGTH_SHORT).show()
                } else {
                    Log.i("ds3m", "SuasEscolas: teste")

                    escolas?.let {
                        items(it.schools) { escola ->

                            Card(
                                modifier = Modifier
                                    .width(250.dp)
                                    .padding(top = 10.dp),
                                backgroundColor = Color(255, 227, 148, 255),
                                border = BorderStroke(1.dp, Color.Black)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = escola.nome_escola)

                                    Spacer(modifier = Modifier.padding(5.dp))

                                    Button(
                                        onClick = {

                                            val escola = SchoolPost(
                                                escola.id_escola,
                                                idDriver.toString().toInt()
                                            )


                                            val callEscolaDelete = GetFunctionsCall.getEscolaCall()
                                                .deleteDriverSchool(
                                                    escola.id_escola,
                                                    escola.id_motorista
                                                )

                                            callEscolaDelete.enqueue(object : Callback<SchoolPost> {
                                                override fun onResponse(
                                                    call: Call<SchoolPost>,
                                                    response: Response<SchoolPost>
                                                ) {
                                                    Toast.makeText(
                                                        context,
                                                        "Escola deletada com sucesso",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    simulateHotReload(context)
                                                }

                                                override fun onFailure(
                                                    call: Call<SchoolPost>,
                                                    t: Throwable
                                                ) {
                                                    Log.i("ds3m", "$t")
                                                }
                                            })

                                            Toast.makeText(
                                                context,
                                                "Escola deletada com sucesso!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            simulateHotReload(context)


                                        },
                                        shape = CircleShape,
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                    ) {
                                        Image(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "",
                                            modifier = Modifier.height(20.dp)
                                        )
                                    }
                                }
                            }

                        }
                    }
                }


            }

            Spacer(modifier = Modifier.padding(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = saveState, onValueChange = {
                        saveState = it

                        if (it == "" || it == null) {
                            isSaveError
                        } else if (it !== "@") {
                            isSaveError
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 52.dp, end = 52.dp),
                    label = {
                        Text(
                            text = "Adicionar escola",
                            style = TextStyle(
                                color = Color.Black,
                            )
                        )
                    },
                    trailingIcon = {
                        if (isSaveError) Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = ""
                        )
                    },
                    isError = isSaveError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0, 0, 0, 255),
                        unfocusedBorderColor = Color(0, 0, 0, 255)
                    )
                )
                if (isSaveError) {
                    Text(
                        text = "Campos Inválidos",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 52.dp),
                        color = Color.Red,
                        fontSize = 15.sp,
                        textAlign = TextAlign.End
                    )
                }

                Button(
                    onClick = {

                        RegiterNewSchool(
                            escola = saveState,
                            motorista = escolas.schools[0].id_motorista,
                        )

                        Toast.makeText(context, "Escola criada com sucesso!", Toast.LENGTH_SHORT)
                            .show()

//                        simulateHotReload(SuasVansActivity::class.java)
                    },
                    colors = ButtonDefaults.buttonColors(Color(251, 211, 69, 255))
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }
        }

    }
}