package com.example.urbancare.db

import androidx.lifecycle.LiveData
import com.example.urbancare.dao.NotasDao
import com.example.urbancare.entities.Nota

class NotasRepositorio(private val notasDao: NotasDao){

    val allNotas: LiveData<List<Nota>> = notasDao.getNotasOrdenadasId()

    suspend fun insert(nota: Nota){
        notasDao.insert(nota)
    }

    suspend fun updateNota(nota: Nota){
        notasDao.updateNota(nota)
    }

    suspend fun deleteNota(id: Int?){
        notasDao.deleteNota(id)
    }


}