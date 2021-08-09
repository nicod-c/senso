package edu.neo.tp3.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.neo.tp3.model.Persona

@Database (entities = [Persona::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun personas() : PersonaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context) : AppDataBase {
            val tempInstace = INSTANCE

            if (tempInstace != null) {
                return tempInstace
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}
