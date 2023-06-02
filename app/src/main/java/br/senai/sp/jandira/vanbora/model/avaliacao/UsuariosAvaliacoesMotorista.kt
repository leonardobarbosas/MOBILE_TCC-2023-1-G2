package br.senai.sp.jandira.vanbora.model.avaliacao

data class UsuariosAvaliacoesMotorista(
    val data_avaliacao: String,
    val id: Int,
    val id_avaliacao: Int,
    val id_motorista: Int,
    val user: String
)