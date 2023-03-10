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
)

//{
//            "id": 15,
//            "email": "teste@gmail.com",
//            "nome": "teste",
//            "rg": "000000000",
//            "cpf": "00000000000",
//            "telefone": "0000000000",
//            "data_nascimento": "2005-10-13T00:00:00.000Z",
//            "senha": "teste123",
//            "foto": "https://www.meutimao.com.br/fotos-do-corinthians/w941/2021/03/05/marcio_bittencourt_foto_3x4_6zok.jpg"
//        }