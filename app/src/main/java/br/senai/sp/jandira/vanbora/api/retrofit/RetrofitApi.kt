package br.senai.sp.jandira.vanbora.api.retrofit

import br.senai.sp.jandira.vanbora.api.auth.AuthRetrofitService
import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object {
        private lateinit var instance: Retrofit

        fun getRetrofit(): Retrofit {
            if (!::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(ConstantsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return instance
        }
        fun retrofitServiceAuth(): AuthRetrofitService {
            instance = getRetrofit()
            return instance.create(AuthRetrofitService::class.java)
        }
    }

}