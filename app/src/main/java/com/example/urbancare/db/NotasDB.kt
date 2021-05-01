package com.example.urbancare.db

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.urbancare.dao.NotasDao
import com.example.urbancare.entities.Nota
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Nota::class), version = 8, exportSchema = false)
public abstract class NotasDB: RoomDatabase(){

    //yes

    abstract fun NotasDao(): NotasDao

    private class NotasDatabaseCallback(
            private val scope: CoroutineScope) : RoomDatabase.Callback() {


        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch{

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotasDB::class.java,
                    "notas_database"
                )//.fallbackToDestructiveMigration()
                        .addCallback(NotasDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}