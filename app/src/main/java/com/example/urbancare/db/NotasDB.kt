package com.example.urbancare.db

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.urbancare.dao.NotasDao
import com.example.urbancare.entities.Nota
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Nota::class), version = 1, exportSchema = false)
public abstract class NotasDB: RoomDatabase(){


    abstract fun NotasDao(): NotasDao

    companion object {


        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDB::class.java,
                    "notas_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}