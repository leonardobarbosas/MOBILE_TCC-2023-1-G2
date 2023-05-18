package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.contract.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface EscolaCall {

    @GET("schools")
    fun getAllSchools(): Call<EscolaList>

    @GET("driverSchools/{id}")
    fun getSchoolByDriver(@Path("id")id: String): Call<EscolaDriver>


    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("school")
    fun postSchool(@Body nome: TheSchoolPost): Call<SchoolReturnPost>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("driverSchool")
    fun postDriverSchool(@Body escola: SchoolPost): Call<ResponseJson>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @DELETE("driverSchools/delete")
    fun deleteDriverSchool(@Body escola: SchoolPost): Call<ResponseJson>

}