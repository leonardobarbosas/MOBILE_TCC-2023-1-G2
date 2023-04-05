package br.senai.sp.jandira.vanbora.model.user

import java.util.Date

data class Driver(
    val cep: String= "",
    val cnh: String= "",
    val cpf: String= "",
    val data_nascimento: String = "",
    val inicio_carreira: Date,
    val email: String = "",
    val foto: String = "",
    val id: Int = 0,
    val nome: String = "",
    val rg: String = "",
    val senha: String = "",
    val telefone: String = "",
    val avaliacao: Int = 0,
    val descricao: String = ""
){
    override fun toString(): String {
        return "User(id=$id, 'cep=$cep', 'cnh=$cnh' , 'cpf=$cpf', 'data_nascimento=$data_nascimento', 'inicio_carreira=$inicio_carreira' ,'email=$email', 'foto=$foto','nome=$nome', 'rg=$rg', 'senha=$senha', 'telefone=$telefone', 'avaliacao=$avaliacao', 'descricao=$descricao')"
    }
}