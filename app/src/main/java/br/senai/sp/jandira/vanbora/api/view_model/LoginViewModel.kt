package br.senai.sp.jandira.vanbora.api.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.vanbora.api.dao.userdao.UserDao

class LoginViewModel(private val userDao: UserDao): ViewModel() {
    fun validateLogin(email: String, senha: String): Boolean{
        val user = userDao.getUserByEmailAndPassword(email, senha)
        return user != null
    }
}