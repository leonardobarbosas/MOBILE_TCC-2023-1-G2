package br.senai.sp.jandira.vanbora.api.calls.van


import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.driver.ModeloList
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.driver.VanList
import br.senai.sp.jandira.vanbora.model.van.PostPutVan
import br.senai.sp.jandira.vanbora.model.van.PutVan
import retrofit2.Call
import retrofit2.http.*

interface VanCall {

    @GET("van/{id}")
    fun getVanById(@Path("id") id: String): Call<Van>

    @GET("vans")
    fun getAllVans(): Call<VanList>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @PUT("van/{id}")
    fun putVan(@Path("id") id: String, @Body van: PutVan): Call<String>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("van")
    fun saveVan(@Body van: PostPutVan): Call<String>

    @DELETE("van/{id}")
    fun deleteVan(@Path("id") id: Int): Call<String>

    @GET("models")
    fun getAllModels(): Call<ModeloList>
}