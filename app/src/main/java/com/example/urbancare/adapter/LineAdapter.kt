package com.example.urbancare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.R
import com.example.urbancare.dataclasses.NotasData
import kotlinx.android.synthetic.main.recycler_line.view.*

class LineAdapter(val list: ArrayList<NotasData>):RecyclerView.Adapter<LineViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_line, parent, false);
        return LineViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val posicao = list[position]

        holder.nota.text = posicao.Nota
        holder.descricao.text = posicao.Descricao
    }

}

class LineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var nota = itemView.nota
    val descricao = itemView.descricao

}



