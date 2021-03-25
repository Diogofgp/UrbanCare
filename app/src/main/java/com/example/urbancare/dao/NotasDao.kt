package com.example.urbancare.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.urbancare.entities.Nota

@Dao
interface NotasDao {

    @Query("SELECT * from notas_table ORDER BY id")
    fun getNotasOrdenadasId(): LiveData<List<Nota>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: Nota)

    @Update
    suspend fun updateNota(nota: Nota)

    @Query("DELETE FROM notas_table where id ==:id")
    suspend fun deleteNota(id: Int?)


}