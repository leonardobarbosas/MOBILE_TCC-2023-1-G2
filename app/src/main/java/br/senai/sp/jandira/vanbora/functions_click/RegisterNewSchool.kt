package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.simulateHotReload
import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.*
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegiterNewSchool(
    escola: String,
    motorista: Int,
) {

    var school = TheSchoolPost(
        nome = escola
    )

    val schoolCallSave = GetFunctionsCall.getEscolaCall().postSchool(nome = school)

    schoolCallSave.enqueue(object : Callback<SchoolReturnPost> {
        override fun onResponse(call: Call<SchoolReturnPost>, response: Response<SchoolReturnPost>)
        {
            if (response.code() == 201) {
                if (response.body()!!.id.toString() != "") {
                    val escolaDriverPost = SchoolPost(
                        id_motorista = motorista,
                        id_escola = response.body()!!.id
                    )


                    val escolaDriverPostCall =
                        GetFunctionsCall.getEscolaCall().postDriverSchool(escola = escolaDriverPost)

                    escolaDriverPostCall.enqueue(object : Callback<ResponseJson> {
                        override fun onResponse(
                            call: Call<ResponseJson>,
                            responseDriver: Response<ResponseJson>
                        ) {
                            if (responseDriver.code() == 201) {
                                Log.i("ds3m", "onResponse: ${responseDriver.body()!!.message}")
                            } else {
                                Log.i("ds3m", "onResponse  ttttttt: ${responseDriver.code()}")
                            }
                        }

                        override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
                            Log.i("ds3m", "onResponse: ${t.message}")
                        }
                    })
                }
            } else {
                Log.i("ds3m", "onResponse: ${response.message()}  ${response.code()}")
            }
        }

        override fun onFailure(call: Call<SchoolReturnPost>, t: Throwable) {
            Log.i("ds3m", "onResponse: ${t.message}")
        }
    })


    simulateHotReload(SuasVansActivity::class.java)


}