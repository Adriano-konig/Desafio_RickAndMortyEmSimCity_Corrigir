package br.com.zup.desafiorickandmortyemsimcity.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.desafiorickandmortyemsimcity.data.datasource.local.dao.PersonagemDao
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult

@Database(entities = [PersonagensResult::class], version = 2)
abstract class PersonagemDatabase: RoomDatabase() {
    abstract fun personagensDao(): PersonagemDao

    companion object{
        @Volatile
        private var INSTANCE: PersonagemDatabase? = null

        fun getPersonagensDataBase(context: Context): PersonagemDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "personagem_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}