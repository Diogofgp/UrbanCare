package com.example.urbancare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.R
import com.example.urbancare.entities.Nota

class NotasAdapter internal constructor(context: Context):
        RecyclerView.Adapter<NotasAdapter.NotasViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>()

    class NotasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notaItemView: TextView = itemView.findViewById(R.id.titulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_line, parent, false)
        return NotasViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val posicao = notas[position]

        holder.notaItemView.text = posicao.id.toString() + "-" + posicao.titulo + "-" + posicao.descricao
    }

    internal fun setNotas(notas: List<Nota>){
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size

}




