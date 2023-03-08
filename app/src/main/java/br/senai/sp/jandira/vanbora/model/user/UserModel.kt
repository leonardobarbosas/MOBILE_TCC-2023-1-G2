package br.senai.sp.jandira.vanbora.model.user

data class UserModel(
    val id: Int = 0,
    val cpf: String = "",
    val data_nascimento: String = "",
    val email: String = "",
    val foto: String = "",
    val nome: String = "",
    val rg: String = "",
    val senha: String = "",
    val telefone: String = ""
){
    override fun toString(): String {
        return "UserModelItem(id=$id, cpf='$cpf', data_nascimento='$data_nascimento', email='$email', foto='$foto', nome='$nome', rg='$rg', senha='$senha', telefone='$telefone')"
    }
}