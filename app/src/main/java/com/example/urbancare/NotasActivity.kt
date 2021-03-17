package com.example.urbancare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.adapter.LineAdapter
import com.example.urbancare.dataclasses.NotasData

class NotasActivity : AppCompatActivity() {

    private lateinit var listaNotas: ArrayList<NotasData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        listaNotas = ArrayList<NotasData>()
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)


        for(i in 0 until 500){
            listaNotas.add(NotasData("Nota $i", "Descricao $i"))
        }
        recycler_view.adapter = LineAdapter(listaNotas)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    /*
    fun insert(view: View){
        listaNotas.add(0, NotasData("Nota xxx", "Descricao xxx"))
        recycler_view.adapter?.notifyDataSetChanged()
    }*/
}