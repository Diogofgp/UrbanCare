package com.example.urbancare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.urbancare.api.EndPoints
import com.example.urbancare.api.OutputLogin
import com.example.urbancare.api.OutputReport
import com.example.urbancare.api.Report
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import ipvc.estg.retrofit.api.ServiceBuilder
import okhttp3.*
import java.io.*

class AdicionarReport : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var image: ImageView
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var location: LatLng
    private val newOcorrActivityRequestCode = 1
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var button: Button
    private lateinit var buttonAdd: Button
    private lateinit var spinner: Spinner
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_report)

        title = findViewById(R.id.add_titulo_report)
        description = findViewById(R.id.add_desc_report)
        image = findViewById(R.id.imagemReport)
        button = findViewById(R.id.btnAdicionarImagem)
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK)
            gallery.type = "image/*"
            startActivityForResult(gallery, pickImage)
        }
        buttonAdd = findViewById(R.id.report_button)
        buttonAdd.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(title.text) && TextUtils.isEmpty(description.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
                startActivityForResult(intent, newOcorrActivityRequestCode)
                Toast.makeText(applicationContext, R.string.toast_preencher_campos, Toast.LENGTH_LONG).show()
            } else {
                addReport()
                //finish()
            }
        }

        spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(this@AdicionarReport, R.array.type,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastLocation = p0.lastLocation
                val loc = LatLng(lastLocation.latitude, lastLocation.longitude)

            }
        }
        createLocationRequest()
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun addReport(){
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val users_id: String? = sharedPreferences.getString(getString(R.string.id_sharedpref), "")

        Log.d("ocaralhoquetefoda", users_id!!)
        val imgBitmap: Bitmap = findViewById<ImageView>(R.id.imagemReport).drawable.toBitmap()
        val imageFile: File = convertBitmapToFile("file", imgBitmap)
        val imgFileRequest: RequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
        val fotografia1: MultipartBody.Part = MultipartBody.Part.createFormData("fotografia", imageFile.name, imgFileRequest)

        val title1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), title.text.toString())

        val description1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), description.text.toString())
        val tipo1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), spinner.selectedItem.toString())
        val latitude1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), lastLocation.latitude.toString())
        val longitude1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), lastLocation.longitude.toString())
        val users_id1: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), users_id)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.addReport(title1, description1, latitude1, longitude1, fotografia1, tipo1, users_id1)

        call.enqueue(object : retrofit2.Callback<OutputReport> {
            override fun onResponse(call: retrofit2.Call<OutputReport>, response: retrofit2.Response<OutputReport>) {
                if(response.isSuccessful){
                    Toast.makeText(this@AdicionarReport, "inseriu com sucesso", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@AdicionarReport, MapsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: retrofit2.Call<OutputReport>, t: Throwable) {
                Toast.makeText(this@AdicionarReport, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        parent.getItemAtPosition(pos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            if (data != null){
                image.setImageURI(data?.data)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    public override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    private fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        //create a file to write bitmap data
        val file = File(this@AdicionarReport.cacheDir, fileName)
        file.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos)
        val bitMapData = bos.toByteArray()

        //write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }
}