package com.example.urbancare

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.adapters.DESCRIPTION
import com.example.urbancare.adapters.NotasAdapter
import com.example.urbancare.adapters.TITLE
import com.example.urbancare.entities.Nota
import com.example.urbancare.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.recycler_line.*

class NotasActivity : AppCompatActivity() {

    private lateinit var notasViewModel: NotasViewModel
    private val newNotaActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        //recycler view
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = NotasAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)


        //view model
        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)
        notasViewModel.allNotas.observe(this, Observer { notas ->
            notas?.let { adapter.setNotas(it) }
        })


        //floating action button (fab)

        val fab = findViewById<FloatingActionButton>(R.id.fab_report)
        fab.setOnClickListener {
            val intent = Intent(this@NotasActivity, AdicionarNota::class.java)
            startActivityForResult(intent, newNotaActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newNotaActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val ptitulo = data?.getStringExtra(AdicionarNota.EXTRA_REPLY_TITULO)
            val pdescricao = data?.getStringExtra(AdicionarNota.EXTRA_REPLY_DESCRICAO)

            if(ptitulo != null && pdescricao != null){
                val nota = Nota(titulo = ptitulo, descricao = pdescricao)
                notasViewModel.insert(nota)
            }

        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }


    fun verNota(view: View) {
        val intent = Intent(this, VerNota::class.java).apply {}
        startActivity(intent)
    }

}

