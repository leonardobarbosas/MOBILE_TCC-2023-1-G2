package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.UserList
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
        foto = foto,
        nome = nome,
        rg = rg,
        senha = senha,
        telefone = telefone
    )

    val userCallSave = GetFunctionsCall.getUserCall().saveUser(user)

    userCallSave.enqueue(object : Callback<UserList>{
        override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
            val new = response.body()!!
            Log.i("ds3x", "onResponse: ${new.users[1].id}")
        }

        override fun onFailure(call: Call<UserList>, t: Throwable) {
            Log.i("ds3x", "onFailure: $t")
        }
    })
}