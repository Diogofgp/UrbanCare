package com.example.urbancare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_adicionar_nota.*

class AdicionarNota : AppCompatActivity() {

    private lateinit var tituloText: EditText
    private lateinit var descricaoText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_nota)

        tituloText = findViewById(R.id.adicionar_titulo)
        descricaoText = findViewById(R.id.adicionar_descricao)

        val button = findViewById<Button>(R.id.button_adicionar_nota)

        //prob errado

        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(tituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_TITULO, tituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESCRICAO, descricaoText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY_TITULO = "com.example.android.titulo"
        const val EXTRA_REPLY_DESCRICAO = "com.example.android.descricao"
    }

}