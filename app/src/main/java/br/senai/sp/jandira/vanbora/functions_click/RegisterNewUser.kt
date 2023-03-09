package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.widget.Toast
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewUser(
    nome: String,
    email: String,
    senha: String,
    rg: String,
    cpf: String,
    telefone: String,
    dataNascimento: String,
    foto: String ,
    context: Context
) {

    val user = UserModel(
        nome = nome,
        email = email,
        senha = senha,
        rg = rg,
        cpf = cpf,
        telefone = telefone,
        data_nascimento = dataNascimento,
        foto = foto
    )

    val callFunction = GetFunctionsCall.getUserCall().saveUser(user)

    callFunction.enqueue(object : Callback<UserModel>{
        override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
            val newUser = response.body()!!
            Toast.makeText(context, "Seu usuário: $nome foi cadastrado com sucesso", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<UserModel>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}