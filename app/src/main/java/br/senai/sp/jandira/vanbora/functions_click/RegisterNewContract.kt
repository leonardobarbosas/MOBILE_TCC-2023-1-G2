package br.senai.sp.jandira.vanbora.functions_click

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.senai.sp.jandira.vanbora.MainActivity
import br.senai.sp.jandira.vanbora.call_functions.GetFunctionsCall
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.model.contract.Escola
import br.senai.sp.jandira.vanbora.model.contract.TipoContrato
import br.senai.sp.jandira.vanbora.model.contract.TipoPagamento
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.ui.activities.client.MotoristasActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//fun RegisterNewContract(
//    nomeResponsavel: String,
//    nomePassageiro: String,
//    idadePassageiro: String,
//    tipoTransporte: TipoContrato,
//    escola: Escola,
//    tipoPagamento: TipoPagamento,
//    usuario: User,
//    motorista: Driver,
//    context: Context
//) {
//
//    val contract = ContractX(
//        nome_responsavel = nomeResponsavel,
//        nome_passageiro = nomePassageiro,
//        idade_passageiro = idadePassageiro,
//        tipo_contrato = tipoTransporte.id,
//        escola = escola,
//        tipo_pagamento = tipoPagamento.id,
//        usuario = usuario,
//        motorista = motorista
//    )
//
//    val contractCallSave = GetFunctionsCall.getContractCall().postContract(contract)
//
//    contractCallSave.enqueue(object : Callback<ContractX>{
//        override fun onResponse(call: Call<ContractX>, response: Response<ContractX>) {
//            val teste = response.body()!!
//            Log.i("ds3m", "onResponse: $teste")
//            Toast.makeText(context, "Contrato criado com sucesso!", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onFailure(call: Call<ContractX>, t: Throwable) {
//            Log.i("ds3m", "onFailure: ${t.message.toString()}")
//        }
//    })
//
//    context.startActivity(Intent(context, MotoristasActivity::class.java))
//
//
//}