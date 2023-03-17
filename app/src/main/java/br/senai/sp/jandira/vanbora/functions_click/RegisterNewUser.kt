package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewUser(
    cep: String,
    cpf: String,
    data_nascimento: String,
    email: String,
    foto: String,
    nome: String,
    rg: String,
    senha: String,
    telefone: String,
    context: Context
) {

    val user = User(
        cep = cep,
        cpf = cpf,
        data_nascimento = data_nascimento,
        email = email,
        foto = foto,
        nome = nome,
        rg = rg,
        senha = senha,
        telefone = telefone
    )

    val userCallSave = GetFunctionsCall.getUserCall().saveUser(user)

    userCallSave.enqueue(object : Callback<User>{
        override fun onResponse(call: Call<User>, response: Response<User>) {
            val teste = response.body()!!
            Log.i("ds3m", "onResponse: ${teste.nome}")
            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message.toString()}")
        }
    })

    context.startActivity(Intent(context, MainActivity::class.java))
}