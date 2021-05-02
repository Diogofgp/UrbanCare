package com.example.urbancare

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.urbancare.api.EndPoints
import com.example.urbancare.api.OutputLogin
import com.example.urbancare.api.User
import ipvc.estg.retrofit.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE)

        val guardarUser= sharedPreferences.getString(getString(R.string.sound), "")

        if(guardarUser != null){
            val intent = Intent(this@MainActivity, MapaActivity::class.java)
            startActivity(intent)
        }

        var btn_login = findViewById<Button>(R.id.login_button)

        btn_login.setOnClickListener {
            login()
        }

    }


    fun login() {

        var user = findViewById<EditText>(R.id.inserir_nome)
        var pass = findViewById<EditText>(R.id.inserir_pass)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.login(username = user.text.toString(), password = pass.text.toString())

        call.enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: retrofit2.Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful){
                    //val c: OutputLogin = response.body()!!
                    //Toast.makeText(this@MainActivity, c.MSG, Toast.LENGTH_SHORT).show()

                    //shared prefs

                    val sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()){
                        putString(getString(R.string.sound), response.body()!!.username)
                        commit()
                    }


                    val intent = Intent(this@MainActivity, MapaActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    //move to note list activity
    fun moverParaNotas(view: View) {
        val intent = Intent(this, NotasActivity::class.java)
        startActivity(intent)
    }

}