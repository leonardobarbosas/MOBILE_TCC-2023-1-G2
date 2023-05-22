package br.senai.sp.jandira.vanbora.roomData.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.senai.sp.jandira.vanbora.roomData.model.LoginDataEntity

@Dao
interface LoginDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loginData: LoginDataEntity)
}