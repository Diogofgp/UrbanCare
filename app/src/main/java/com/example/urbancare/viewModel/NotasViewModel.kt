package com.example.urbancare.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.urbancare.dao.NotasDao
import com.example.urbancare.db.NotasDB
import com.example.urbancare.db.NotasDB.Companion.getDatabase
import com.example.urbancare.db.NotasRepositorio
import com.example.urbancare.entities.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotasViewModel(application: Application): AndroidViewModel(application){

    private val repositorio: NotasRepositorio
    val allNotas: LiveData<List<Nota>>

    init{
        val notasDao = NotasDB.getDatabase(application, viewModelScope).NotasDao()
        repositorio = NotasRepositorio(notasDao)
        allNotas = repositorio.allNotas
    }

    fun insert(nota: Nota) = viewModelScope.launch(Dispatchers.IO){
        repositorio.insert(nota)
    }

}