package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.LoginUserClientJwtModel
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun LoginUserCLient(emailProps: String, senhaProps: String, context: Context){
    val userClient = User(email = emailProps, senha = senhaProps)

    val userClientCallLogin = GetFunctionsCall.getUserCall().loginUserClient(user = userClient)

    userClientCallLogin.enqueue(object : Callback<LoginUserClientJwtModel>{
        override fun onResponse(
            call: Call<LoginUserClientJwtModel>,
            response: Response<LoginUserClientJwtModel>
        ) {
            val userLogin = response.body()!!

            if(userLogin != null ){
                val intentSelect = Intent(context, MotoristasActivity::class.java)

                intentSelect.putExtra("id", userLogin.id.toString())

                context.startActivity(intentSelect)

            }
        }

        override fun onFailure(call: Call<LoginUserClientJwtModel>, t: Throwable) {
            Log.i("ds3m", "onFailure: $t")
        }
    })
}