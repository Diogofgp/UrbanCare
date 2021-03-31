package com.example.urbancare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //move to note list activity
    fun moverParaNotas(view: View) {
        val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
    }

    //move to note list activity
    fun moverParaMapa(view: View) {
        //val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
        //s
    }

}