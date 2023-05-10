package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.ContractPost
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.model.contract.Escola
import br.senai.sp.jandira.vanbora.model.contract.ResponseJson
import br.senai.sp.jandira.vanbora.model.contract.TipoContrato
import br.senai.sp.jandira.vanbora.model.contract.TipoPagamento
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun RegisterNewContract(
    nomePassageiro: String,
    idadePassageiro: String,
    tipoTransporte: Int,
    escola: Int,
    tipoPagamento: Int,
    usuario: Int,
    motorista: Int,
    context: Context
) {

    val contract = ContractPost(
        nome_passageiro = nomePassageiro,
        idade_passageiro = idadePassageiro,
        id_tipo_contrato = tipoTransporte,
        id_escola = escola,
        id_tipo_pagamento = tipoPagamento,
        id_usuario = usuario,
        id_motorista = motorista
    )

    val contractCallSave = GetFunctionsCall.getContractCall().postContract(contract)

    contractCallSave.enqueue(object :Callback<ResponseJson>{
        override fun onResponse(call: Call<ResponseJson>, response: Response<ResponseJson>) {
            var contract = response
            Log.i("ds3m", "response.body: $contract")
        }

        override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
            Log.i("ds3m", "onFailure: ${t.message.toString()}")
        }
    })


    context.startActivity(Intent(context, MotoristasActivity::class.java))


}