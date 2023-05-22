package br.senai.sp.jandira.vanbora.api.calls.comments

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.comment.Comment
import br.senai.sp.jandira.vanbora.model.comment.CommentPost
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import retrofit2.Call
import retrofit2.http.*

interface CommentCall {

    @GET("comments/driver/{id}")
    fun getCommentByDriver(@Path("id")id: String): Call<Comment>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("comment")
    fun postComment(@Body comment: CommentPost): Call<ResponseJson>

    @DELETE("comment/{id}")
    fun deleteComment(@Path("id") id: Int): Call<String>

}