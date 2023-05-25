package br.senai.sp.jandira.vanbora.model.dto

data class JwtPayload(
    val id: String? = null,
    val email: String? = null,
    val type: String? = null,
    val iat: Long? = null,
    val exp: Long? = null
)