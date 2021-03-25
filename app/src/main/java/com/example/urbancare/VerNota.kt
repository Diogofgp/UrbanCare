package com.example.urbancare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbancare.AdicionarNota.Companion.EXTRA_REPLY_DESCRICAO
import com.example.urbancare.AdicionarNota.Companion.EXTRA_REPLY_TITULO
import com.example.urbancare.adapters.DESCRIPTION
import com.example.urbancare.adapters.ID
import com.example.urbancare.adapters.NotasAdapter
import com.example.urbancare.adapters.TITLE
import com.example.urbancare.entities.Nota
import com.example.urbancare.viewModel.NotasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_ver_nota.*

class VerNota : AppCompatActivity() {

    private lateinit var editarTituloText: EditText
    private lateinit var editarDescricaoText: EditText
    private lateinit var notasViewModel: NotasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_nota)

        //editarTituloText = findViewById(R.id.editar_titulo)
        //editarDescricaoText = findViewById(R.id.editar_descricao)

        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)

        //ver info
        val getTitle = findViewById<EditText>(R.id.editar_titulo)
        getTitle.setText(intent.getStringExtra(TITLE))
        val getDescription = findViewById<EditText>(R.id.editar_descricao)
        getDescription.setText(intent.getStringExtra(DESCRIPTION))

        var id_nota = intent.getIntExtra(ID, 0)

        val btn = findViewById<Button>(R.id.edit_button)

        btn.setOnClickListener {
            //go back to previous activity
            val intent = Intent(this, NotasActivity::class.java)

            val nota = Nota(id_nota, getTitle.text.toString(), getDescription.text.toString())

            notasViewModel.updateNota(nota)
            startActivity(intent)

        }

        //findViewById<EditText>(R.id.editar_titulo).setText(getTitle)
        //findViewById<EditText>(R.id.editar_descricao).setText(getDescription)



        /*
        edit_button.setOnClickListener {
            val replyIntent = Intent()


            if (TextUtils.isEmpty(editarTituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_TITULO, editarTituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESCRICAO, editarDescricaoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }*/

    }


    /*
    fun editarNota(view: View){


        editarTituloText = findViewById(R.id.editar_titulo)
        editarDescricaoText = findViewById(R.id.editar_descricao)

        var id_nota = intent.getIntExtra(ID, 0)
        val replyIntent = Intent()

        if (TextUtils.isEmpty(editarTituloText.text)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {


            val nota = Nota(id= id_nota, titulo = editarTituloText.text.toString(), descricao = editarDescricaoText.text.toString())

            notasViewModel.updateNota(nota)
            setResult(Activity.RESULT_OK, replyIntent)

            finish()
        }

    }*/

    fun voltar(view: View) {

        val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
    }


}