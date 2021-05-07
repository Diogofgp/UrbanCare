package com.example.urbancare

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.urbancare.api.EndPoints
import com.example.urbancare.api.Report
import com.squareup.picasso.Picasso
import ipvc.estg.retrofit.api.ServiceBuilder
import ipvc.estg.retrofit.api.ServiceBuilder.buildService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerReport : AppCompatActivity() {

    private lateinit var reports: List<Report>
    private lateinit var desc: TextView
    private lateinit var tit: TextView
    private lateinit var foto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_report)

        desc = findViewById(R.id.receberDesc)
        tit = findViewById(R.id.receberTitulo)
        foto = findViewById(R.id.verFoto)

        val request = buildService(EndPoints::class.java)
        val call = request.getReports()
        call.enqueue(object : Callback<List<Report>> {
            override fun onResponse(call: Call<List<Report>>, response: Response<List<Report>>) {
                if (response.isSuccessful) {
                    reports = response.body()!!
                    for (report in reports) {
                        desc.setText(report.descricao)
                        tit.setText(report.titulo)
                        Picasso.with(this@VerReport).load("http://10.0.2.2/myslim/fotos/" + report.fotografia + ".png").into(foto)
                    }
                }
            }

            override fun onFailure(call: Call<List<Report>>, t: Throwable) {
                Toast.makeText(this@VerReport, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}