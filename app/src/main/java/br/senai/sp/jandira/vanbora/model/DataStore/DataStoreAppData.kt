package br.senai.sp.jandira.vanbora.model.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreAppData(private val  context: Context) {

    //Unindo tudo em uma instância
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
        val TOKEN = stringPreferencesKey("auth_token")
        val TYPE = stringPreferencesKey("type_user")
        val ID_USER = stringPreferencesKey("id_user")
        val NAME_USER = stringPreferencesKey("name_user")
        val NAME_REGISTER = stringPreferencesKey("name_register")
        val EMAIL_REGISTER = stringPreferencesKey("email_user")
        val PASSWORD_REGISTER = stringPreferencesKey("password_register")
    }

    //Pegar Token
    val getToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    val getNameRegister: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_REGISTER] ?: ""
    }

    val getEmailRegister: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EMAIL_REGISTER] ?: ""
    }

    val getPasswordRegister: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PASSWORD_REGISTER] ?: ""
    }

    val getNameUser: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_USER] ?: ""
    }

    val getIdUSer: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[ID_USER] ?: ""
    }

    val getType: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TYPE] ?: ""
    }

    suspend fun saveIdUser(idUser: String) {
        context.dataStore.edit {preferences ->
            preferences[ID_USER] = idUser
        }
    }

    suspend fun saveNameRegister(name: String) {
        context.dataStore.edit {preferences ->
            preferences[NAME_REGISTER] = name
        }
    }

    suspend fun saveEmailRegister(email: String) {
        context.dataStore.edit {preferences ->
            preferences[EMAIL_REGISTER] = email
        }
    }

    suspend fun savePasswordRegister(password: String) {
        context.dataStore.edit {preferences ->
            preferences[PASSWORD_REGISTER] = password
        }
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_USER] = name
        }
    }

    suspend fun saveType(type: String) {
        context.dataStore.edit { preferences ->
            preferences[TYPE] = type
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }

}