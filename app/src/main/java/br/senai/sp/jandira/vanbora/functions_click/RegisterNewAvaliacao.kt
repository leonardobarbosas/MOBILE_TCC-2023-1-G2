package br.senai.sp.jandira.vanbora.functions_click

import android.util.Log
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.avaliacao.AvaliacaoReturnPost
import br.senai.sp.jandira.vanbora.model.avaliacao.PostAvaliacao
import br.senai.sp.jandira.vanbora.model.comment.CommentPost
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewAvaliacao(
    avaliacao: Int,
    usuario: Int,
    motorista: Int
) {

    val avaliacoes = PostAvaliacao(
        id_avaliacao = avaliacao,
        id_motorista = motorista,
        id_usuario = usuario
    )

    val commentSaveCall = GetFunctionsCall.getAvaliacaoCall().postAvaliacao(avaliacoes)

    commentSaveCall.enqueue(object: Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            var avaliacoes = response
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            Log.i("ds3m", "onFailure registerNewValaciad: ${t.message.toString()}")
        }

    })

}