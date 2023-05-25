package br.senai.sp.jandira.vanbora.api.auth

import br.senai.sp.jandira.vanbora.model.dto.AuthDTO
import br.senai.sp.jandira.vanbora.model.dto.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitService {
    @POST("login")
    fun login(@Body authDTO: AuthDTO) : Call<LoginResponse>
}