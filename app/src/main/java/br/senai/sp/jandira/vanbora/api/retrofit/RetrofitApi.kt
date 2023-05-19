package br.senai.sp.jandira.vanbora.api.retrofit

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall.Companion.retrofit
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.room.dao.ApiService
import br.senai.sp.jandira.vanbora.room.model.LoginCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        fun getRetrofit(): Retrofit{
            if(!::instance.isInitialized){
                instance = Retrofit
                    .Builder()
                    .baseUrl(ConstantsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val apiService = retrofit.create(ApiService::class.java)
                suspend fun loginUser(credentials: LoginCredentials): User {
                    return withContext(Dispatchers.IO) {
                        try {
                            apiService.loginUser(credentials)
                        } catch (e: Exception) {

                            throw e
                        }
                    }
                }
            }

            return instance
        }
    }

}