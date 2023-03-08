package br.senai.sp.jandira.vanbora.call_functions

import br.senai.sp.jandira.vanbora.api.calls.user.UserCall
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import retrofit2.Call

class GetFunctionsCall {

    companion object{
        val retrofit = RetrofitApi.getRetrofit()
        fun getUserCall(): UserCall{
            val userCall = retrofit.create(UserCall::class.java)

            return userCall
        }
    }

}