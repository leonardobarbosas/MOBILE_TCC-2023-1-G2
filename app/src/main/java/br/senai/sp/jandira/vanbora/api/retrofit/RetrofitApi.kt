package br.senai.sp.jandira.vanbora.api.retrofit

import br.senai.sp.jandira.vanbora.api.MyInterceptor
import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        private var client =  OkHttpClient.Builder().apply {
            addInterceptor(MyInterceptor())
        }.build()

        fun getRetrofit(): Retrofit{
            if(!::instance.isInitialized){
                instance = Retrofit.Builder()
                    .baseUrl(ConstantsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return instance
        }
    }

}