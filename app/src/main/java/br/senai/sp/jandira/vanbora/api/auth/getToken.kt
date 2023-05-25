package br.senai.sp.jandira.vanbora.api.auth

import android.content.Context
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi
import br.senai.sp.jandira.vanbora.model.DataStore.DataStoreAppData
import br.senai.sp.jandira.vanbora.model.dto.AuthDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun getToken (context: Context, authDTO: AuthDTO, onComplete: (String) -> Unit){
    val call = RetrofitApi.retrofitServiceAuth().login(authDTO)
    val scope = CoroutineScope(Dispatchers.Main)
    val dataStore = DataStoreAppData(context = context)

}