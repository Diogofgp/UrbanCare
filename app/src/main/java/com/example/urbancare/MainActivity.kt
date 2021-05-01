package com.example.urbancare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.urbancare.api.EndPoints
import ipvc.estg.retrofit.api.ServiceBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //

    /*
    fun login(view: View) {

        var user = findViewById<EditText>(R.id.inserir_nome)
        var pass = findViewById<EditText>(R.id.inserir_pass)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.login(username = "", password = "")

        call.enqueue(object : retrofit2.Callback<OutputPost> {
            override fun onResponse(call: retrofit2.Call<OutputPost>, response: retrofit2.Response<OutputPost>) {
                if (response.isSuccessful){
                    val c: OutputPost = response.body()!!
                    Toast.makeText(this@MainActivity, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<OutputPost>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    //move to note list activity
    fun moverParaNotas(view: View) {
        val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
    }

    /*
    fun moverMapa(view: View) {
        val intent = Intent(this, MapaActivity::class.java)
        startActivity(intent)
    }*/

}
