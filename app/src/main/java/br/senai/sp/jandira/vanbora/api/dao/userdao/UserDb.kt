package br.senai.sp.jandira.vanbora.api.dao.userdao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.vanbora.model_room.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UserDb: RoomDatabase() {

    abstract fun usuarioDao(): UserDao

    companion object{
        private lateinit var instance: UserDb

        fun getDatabase(context: Context): UserDb{
            if (!Companion::instance.isInitialized){
                instance = Room.databaseBuilder(context, UserDb::class.java, "db_usuario").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}