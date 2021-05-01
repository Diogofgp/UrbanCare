package com.example.urbancare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.urbancare.api.EndPoints
import com.example.urbancare.api.OutputLogin
import ipvc.estg.retrofit.api.ServiceBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {

        var user = findViewById<EditText>(R.id.inserir_nome)
        var pass = findViewById<EditText>(R.id.inserir_pass)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.login(username = user.text.toString(), password = pass.text.toString())

        call.enqueue(object : retrofit2.Callback<OutputLogin> {
            override fun onResponse(call: retrofit2.Call<OutputLogin>, response: retrofit2.Response<OutputLogin>) {
                if (response.isSuccessful){
                    val c: OutputLogin = response.body()!!
                    Toast.makeText(this@MainActivity, c.MSG+ "-" + c.status, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<OutputLogin>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
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
    }

}