package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.Modelo
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.driver.post.DriverPost
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.model.van.PostPutVan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewDriver(
    postVan: PostPutVan,
    context: Context,
) {

    val postVanDriver = GetFunctionsCall.getVanCall().saveVan(postVan)

    postVanDriver.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if(response.isSuccessful){
                Toast.makeText(context, "Você foi cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message} van")
        }
    })

}