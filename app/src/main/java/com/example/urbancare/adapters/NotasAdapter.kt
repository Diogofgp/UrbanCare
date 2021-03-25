package com.example.urbancare.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.NotasActivity
import com.example.urbancare.R
import com.example.urbancare.VerNota
import com.example.urbancare.entities.Nota
import com.example.urbancare.viewModel.NotasViewModel
import kotlinx.android.synthetic.main.recycler_line.view.*

const val TITLE = "title"
const val ID = "ID"
const val DESCRIPTION = "description"

class NotasAdapter internal constructor(context: Context):
        RecyclerView.Adapter<NotasAdapter.NotasViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notas = emptyList<Nota>()


    class NotasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notaItemView: TextView = itemView.findViewById(R.id.titulo)
        val descricaoItemView: TextView = itemView.findViewById(R.id.descricao)
        val verNota: LinearLayout = itemView.findViewById(R.id.linha_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_line, parent, false)
        return NotasViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val posicao = notas[position]

        //show info on recycler view
        holder.notaItemView.text = posicao.titulo
        holder.descricaoItemView.text = posicao.descricao


        //show info when note is clicked
        holder.verNota.setOnClickListener {
            val context = holder.notaItemView.context
            val title = holder.notaItemView.text
            val description = holder.descricaoItemView.text
            val id: Int? = posicao.id


            val intent = Intent(context, VerNota::class.java).apply {
                putExtra(TITLE, title)
                putExtra(DESCRIPTION, description)
                putExtra(ID, id)
            }
            context.startActivity(intent)
        }

    }

    fun deleteNota(holder: NotasViewHolder, position: Int){

    }

    internal fun setNotas(notas: List<Nota>){
        this.notas = notas
        notifyDataSetChanged()
    }

    override fun getItemCount() = notas.size

}




