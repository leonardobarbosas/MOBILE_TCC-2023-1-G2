package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
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
        cpf = cpf,
        data_nascimento = dataNascimento,
        email = email,
        foto = "sdfsfsfse",
        nome = nome,
        rg = rg,
        senha = senha,
        telefone = telefone
    )

    val userCallSave = GetFunctionsCall.getUserCall().saveUser(user)

    userCallSave.enqueue(object : Callback<UserModel>{
        override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
            val newUser = response.body()!!
            Toast.makeText(context, "${newUser.id} - ${newUser.nome}", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<UserModel>, t: Throwable) {
            Toast.makeText(context, "Sem net", Toast.LENGTH_SHORT).show()
        }
    })
}