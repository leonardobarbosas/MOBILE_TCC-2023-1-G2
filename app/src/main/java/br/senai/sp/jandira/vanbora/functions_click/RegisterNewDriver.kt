package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.van.PostPutVan
import br.senai.sp.jandira.vanbora.ui.activities.driver.SuasVansActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewDriver(
    postVan: PostPutVan,
    context: Context,
) {

    val id = postVan.id_motorista

    Log.i("ds3m", "RegisterNewDriver: $id")

    val postVanDriver = GetFunctionsCall.getVanCall().saveVan(postVan)

    postVanDriver.enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if(response.isSuccessful){
                val intentS = Intent(context, SuasVansActivity::class.java)
                intentS.putExtra("id", id.toString())
                Toast.makeText(context, "Você foi cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                context.startActivity(intentS)
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message} van")
        }
    })

}