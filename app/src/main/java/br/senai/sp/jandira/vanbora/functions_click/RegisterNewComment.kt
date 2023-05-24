package br.senai.sp.jandira.vanbora.functions_click

import android.util.Log
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.comment.CommentPost
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewComment(
    comentario: String,
    usuario: Int,
    motorista: Int
) {

    val comment = CommentPost(
        comentario = comentario,
        id_motorista = motorista,
        id_usuario = usuario
    )

    val commentSaveCall = GetFunctionsCall.getCommentCall().postComment(comment)

    commentSaveCall.enqueue(object: Callback<ResponseJson>{
        override fun onResponse(call: Call<ResponseJson>, response: Response<ResponseJson>) {
            var comment = response
        }

        override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message.toString()}")
        }

    })

}