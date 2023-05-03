package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.Modelo
import br.senai.sp.jandira.vanbora.model.driver.Van
import br.senai.sp.jandira.vanbora.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewDriver (
    senha: String,
    email: String,
    nome: String,
    rg: String,
    cpf: String,
    cnh: String,
    telefone: String,
    data_nascimento: String,
    inicio_carreira: String,
    foto: String,
    descricao: String,
    context: Context
){

    val driver = Driver(
        senha = senha,
        email = email,
        nome = nome,
        rg = rg,
        cpf = cpf,
        cnh = cnh,
        telefone = telefone,
        data_nascimento = data_nascimento,
        inicio_carreira = inicio_carreira,
        foto = foto,
        descricao = descricao,
    )

    val driverCallSave = GetFunctionsCall.getDriverCall().saveDriver(driver)

    driverCallSave.enqueue(object : Callback<Driver> {
        override fun onResponse(call: Call<Driver>, response: Response<Driver>) {
            val teste = response.body()!!
            Log.i("ds3m", "onResponse: ${teste.nome}")
            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
        }

        override fun onFailure(call: Call<Driver>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message.toString()}")
        }
    })

    context.startActivity(Intent(context, MainActivity::class.java))

}