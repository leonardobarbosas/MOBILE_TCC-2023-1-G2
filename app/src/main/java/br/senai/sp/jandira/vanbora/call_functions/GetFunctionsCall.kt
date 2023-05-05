package br.senai.sp.jandira.vanbora.call_functions

import br.senai.sp.jandira.vanbora.api.calls.contracts.ContractCall
import br.senai.sp.jandira.vanbora.api.calls.driver.DriverCall
import br.senai.sp.jandira.vanbora.api.calls.user.UserCall
import br.senai.sp.jandira.vanbora.api.calls.van.VanCall
import br.senai.sp.jandira.vanbora.api.retrofit.RetrofitApi

class GetFunctionsCall {

    companion object{
        val retrofit = RetrofitApi.getRetrofit()
        fun getUserCall(): UserCall{
            val userCall = retrofit.create(UserCall::class.java)

            return userCall
        }

        fun getDriverCall(): DriverCall {
            val driverCall = retrofit.create(DriverCall::class.java)

            return driverCall
        }

        fun getContractCall(): ContractCall{
            val contractCall = retrofit.create(ContractCall::class.java)

            return contractCall
        }

        fun getVanCall(): VanCall {
            val vanCall = retrofit.create(VanCall::class.java)

            return vanCall
        }
    }

}