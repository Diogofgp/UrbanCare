package com.example.urbancare

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
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


        notasViewModel = ViewModelProvider(this).get(NotasViewModel::class.java)

        //ver info
        val getTitle = findViewById<EditText>(R.id.editar_titulo)
        getTitle.setText(intent.getStringExtra(TITLE))
        val getDescription = findViewById<EditText>(R.id.editar_descricao)
        getDescription.setText(intent.getStringExtra(DESCRIPTION))

        var id_nota = intent.getIntExtra(ID, 0)

        val btn = findViewById<Button>(R.id.edit_button)


        //update informação
        btn.setOnClickListener {
            //go back to previous activity
            val intent = Intent(this, NotasActivity::class.java)
            val replyIntent = Intent()
            val nota = Nota(id_nota, getTitle.text.toString(), getDescription.text.toString())

            if (TextUtils.isEmpty(getTitle.text) || TextUtils.isEmpty(getDescription.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(this, R.string.toast_preencher_campos, Toast.LENGTH_LONG).show()

            } else {
                notasViewModel.updateNota(nota)
                startActivity(intent)
            }

        }

        val btnDelete = findViewById<Button>(R.id.delete_button)


        //delete notas
        btnDelete.setOnClickListener {
            val intent = Intent(this, NotasActivity::class.java)
            val builder = AlertDialog.Builder(this)

            builder.setPositiveButton("Yes"){ _, _ ->
                notasViewModel.deleteNota(id_nota)
                startActivity(intent)
                Toast.makeText(this, R.string.de_certeza, Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("No"){ _, _ ->}
            builder.setTitle("Delete note with the title: '${getTitle.text}'")
            builder.setMessage(R.string.de_certeza)
            builder.create().show()

        }
    }

}