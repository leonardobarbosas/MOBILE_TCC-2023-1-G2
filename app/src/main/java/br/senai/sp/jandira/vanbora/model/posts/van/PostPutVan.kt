package br.senai.sp.jandira.vanbora.model.posts.van

data class PostPutVan(
    val foto: String,
    val id_modelo: Int,
    val id_motorista: Int,
    val placa: String,
    val quantidade_vagas: String,
    val status_van: Int
)