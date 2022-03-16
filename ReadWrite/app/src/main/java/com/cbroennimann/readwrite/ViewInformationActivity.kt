package com.cbroennimann.readwrite

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException


class ViewInformationActivity : AppCompatActivity() {
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_information)

        // findViewById returns a view, we need to cast it into TextView
        textView = findViewById<View>(R.id.textView_get_saved_data) as TextView
    }

    fun showPublicData(view: View?) {
        // Accessing the saved data from the downloads folder
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        val data = getdata(file)
        if (data != null) {
            textView!!.text = data
        } else {
            textView!!.text = "No Data Found"
        }
    }

    fun showPrivateData(view: View?) {

        // QuintrixTraining is the folder name to access privately saved data
        val folder = this.getExternalFilesDir("AndroidKotlinTraining")

        // QuintrixTrainingPrivateFile.txt is the file that is saved privately
        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        val data = getdata(file)
        if (data != null) {
            textView!!.text = data
        } else {
            textView!!.text = "No Data Found"
        }
    }

    fun back(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    // getdata() is the method which reads the data
    // the data that is saved in byte format in the file
    private fun getdata(myfile: File): String? {
        //var fileInputStream: FileInputStream? = null
        var inputString = ""

        val bufferedReader: BufferedReader = myfile.bufferedReader()


        inputString = bufferedReader.use { it.readText() }

        return inputString
    }
}