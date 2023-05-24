package br.senai.sp.jandira.vanbora.model.comment

import br.senai.sp.jandira.vanbora.model.user.User

data class CommentX(
    val comentario: String,
    val id: Int,
    val id_motorista: Int,
    val user: User
)