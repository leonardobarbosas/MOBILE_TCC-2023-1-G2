package br.senai.sp.jandira.vanbora.api.auth

import android.content.Context
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import br.senai.sp.jandira.vanbora.model.DataStore.DataStoreAppData
import br.senai.sp.jandira.vanbora.model.dto.AuthDTO
import com.auth0.android.jwt.JWT
import br.senai.sp.jandira.vanbora.model.dto.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getToken (context: Context, authDTO: AuthDTO, onComplete: (String) -> Unit){
    val call = RetrofitApi.retrofitServiceAuth().login(authDTO)
    val scope = CoroutineScope(Dispatchers.Main)
    val dataStore = DataStoreAppData(context = context)

    call.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if(response.message().toString().contains("Bad Request") || response.message().toString().contains("Unauthorized")) {
                return onComplete.invoke("Usuário ou senha incorretos!")
            }

            val jwt = JWT(response.body()!!.token)

            scope.launch {
                dataStore.saveToken(response.body()!!.token)
                dataStore.saveType(jwt.getClaim("type").asString().toString())
                dataStore.saveIdUser(jwt.getClaim("id").asString().toString())
                dataStore.saveName(response.body()!!.data.nome)
            }

            onComplete.invoke(response.body()!!.token)
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}