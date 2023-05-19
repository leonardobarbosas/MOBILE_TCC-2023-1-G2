package br.senai.sp.jandira.vanbora.model.comment

data class CommentPost(
    val comentario: String,
    val id_motorista: Int,
    val id_usuario: Int
)