package com.example.urbancare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.urbancare.R

class AdicionarNota: AppCompatActivity(){

    private lateinit var editarNotasView: EditText
    /*
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.)

        editarNotasView = findViewById(R.id.butao_editar)
        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editarNotasView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val nota = editarNotasView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, nota)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        companion object{
            const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        }

    }
    */
}