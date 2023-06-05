package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.model.user.post.PostUser
import br.senai.sp.jandira.vanbora.ui.activities.global.RegisterActivity
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
    numero_casa: String,
    context: Context,
) {

    if (cep != "" && cpf != "" && data_nascimento != "" && email != "" && foto != "" && nome != "" && rg != "" && senha != "" && telefone != "" && numero_casa != "") {
        val userPost = PostUser(
            cep = cep,
            cpf = cpf,
            data_nascimento = data_nascimento,
            email = email,
            foto = foto,
            nome = nome,
            rg = rg,
            senha = senha,
            telefone = telefone,
            numero = numero_casa
        )

        var userCallSave = GetFunctionsCall.getUserCall().saveUser(user = userPost)

        userCallSave.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    Toast.makeText(context, "Perfil cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(context, "Else: Não foi possivel efetuar o cadastro", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "OnFailure: Não foi possivel efetuar o cadastro", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, MainActivity::class.java))
            }

        })
    } else {
        Toast.makeText(context, "Você não preencheu todas as informações necessárias", Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context, RegisterActivity::class.java))
    }


}