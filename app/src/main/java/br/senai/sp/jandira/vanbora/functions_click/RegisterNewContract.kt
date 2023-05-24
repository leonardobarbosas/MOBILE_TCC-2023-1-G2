package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.simulateHotReload
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.ContractPost
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import br.senai.sp.jandira.vanbora.ui.activities.client.ContratoActivity
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewContract(
    contract: ContractPost,
    context: Context
) {
    val contractCallSave = GetFunctionsCall.getContractCall().postContract(contract)

    contractCallSave.enqueue(object : Callback<String>{
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if(response.isSuccessful){

                val intentSelect = Intent(context, MotoristasActivity::class.java)

                intentSelect.putExtra("id", contract.id_usuario.toString())

                context.startActivity(intentSelect)
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message}  aaaaa")
        }
    })

}