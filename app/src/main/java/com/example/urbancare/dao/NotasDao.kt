package com.example.urbancare.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.urbancare.entities.Nota

@Dao
interface NotasDao {

    @Query("SELECT * from notas_table ORDER BY id")
    fun getNotasOrdenadasId(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)
}