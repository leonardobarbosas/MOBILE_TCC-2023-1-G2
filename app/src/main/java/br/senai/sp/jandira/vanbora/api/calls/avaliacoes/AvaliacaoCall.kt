package br.senai.sp.jandira.vanbora.api.calls.avaliacoes

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.avaliacao.AvaliacaoReturnPost
import br.senai.sp.jandira.vanbora.model.avaliacao.PostAvaliacao
import br.senai.sp.jandira.vanbora.model.avaliacao.UsuarioAvaliacaoMotorista
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AvaliacaoCall {

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("usuarioAvaliacaoMotorista")
    fun postAvaliacao(@Body avaliacao: PostAvaliacao): Call<String>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @GET("usuarioAvaliacaoMotorista/motorista/{id}")
    fun getAvaliacao(@Path("id")id: String): Call<UsuarioAvaliacaoMotorista>

}