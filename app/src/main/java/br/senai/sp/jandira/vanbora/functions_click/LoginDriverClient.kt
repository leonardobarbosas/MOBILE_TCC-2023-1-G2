package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.LoginDriverClientJwtModel
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun LoginDriverClient(emailProps: String, senhaProps: String ,context: Context){
    val driverClient = Driver(email = emailProps, senha = senhaProps)

    val driverClientCallLogin = GetFunctionsCall.getDriverCall().loginDriverClient(driver = driverClient)

    driverClientCallLogin.enqueue(object : Callback<LoginDriverClientJwtModel>{
        override fun onResponse(
            call: Call<LoginDriverClientJwtModel>,
            response: Response<LoginDriverClientJwtModel>
        ) {
            val driverLogin = response.body()!!

            if(driverLogin != null ){
                val intentSelect = Intent(context, MotoristasActivity::class.java)

                intentSelect.putExtra("id", driverLogin.id.toString())

                context.startActivity(intentSelect)

            }
        }

        override fun onFailure(call: Call<LoginDriverClientJwtModel>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }

    })
}
