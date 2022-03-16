package com.cbroennimann.readwrite

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {
    // After API 23 the permission request for accessing external storage is changed
    // Before API 23 permission request is asked by the user during installation of app
    // After API 23 permission request is asked at runtime
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    lateinit var  shared: SharedPreferences
    var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared=getSharedPreferences("Test", Context.MODE_PRIVATE)
        val savePrefButton = findViewById<Button>(R.id.savePref)
        val showPrefButton = findViewById<Button>(R.id.viewPref)
        val retrieveButton = findViewById<Button>(R.id.retrieve)
        val editPrefText = findViewById<EditText>(R.id.edt)
        val editPrefView = findViewById<TextView>(R.id.txt)
        val editRetText = findViewById<TextView>(R.id.ret)
        savePrefButton.setOnClickListener {
            val edit = shared.edit()
            edit.putString("txt", editPrefText.text.toString())

            edit.apply()
        }

        showPrefButton.setOnClickListener {
            editPrefView.text = shared.getString("txt", "No imported")
        }

        retrieveButton.setOnClickListener {
            editRetText.text =
                "External files are stored at " +
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

        }

        // findViewById return a view, we need to cast it to EditText View
        editText = findViewById<View>(R.id.editText_data) as EditText
    }

    fun savePublicly(view: View?) {
        // Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )
        val editTextData = editText!!.text.toString()

        // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Storing the data in file with name as QuintrixTraining
        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun savePrivately(view: View?) {
        val editTextData = editText!!.text.toString()

        // Creating folder with name AndroiidKotlinTraining
        val folder = getExternalFilesDir("AndroidKotlinTraining")

        // Creating file with name myfile.txt
        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun viewInformation(view: View?) {
        // Creating an intent to start a new activity
        val intent = Intent(this@MainActivity, ViewInformationActivity::class.java)
        startActivity(intent)
    }

    // writeTextData() method save the data into the file in byte format
    // It also toast a message "Done/filepath_where_the_file_is_saved"
    private fun writeTextData(file: File, data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(data.toByteArray())
            Toast.makeText(this, "Done" + file.absolutePath, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {

            Toast.makeText(this, e.stackTraceToString(),Toast.LENGTH_LONG).show()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}