package com.android.example.usingthreads

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.net.HttpURLConnection
import java.net.URL
import com.android.example.usingthreads.ImageNDescriptions

import com.android.example.usingthreads.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    val imgUrl = ImageNDescriptions().imageList
    val imgDesc =  ImageNDescriptions().descriptionList
    var index = 0
    lateinit var currentJob : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** setup viewbinding */
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        view.setOnTouchListener { _, m: MotionEvent ->
            if(currentJob.isActive && m.getAction() != MotionEvent.ACTION_UP) {
                currentJob.cancel()
            }
            if (m.getAction() == MotionEvent.ACTION_DOWN) {
                generateImage()
            }

            Log.i("CoroutineExercise", "index $index")
            //generateImage()
            true
            }
        generateImage()

    }

    private fun generateImage() {
        if(index == 9) {
            index = 0
        } else {
            index++
        }
         currentJob = GlobalScope.launch {
            val imageUrl = imgUrl[index]
            //Yosemite
            //URL("https://www.nps.gov/yose/planyourvisit/images/20170618_155330.jpg")


            val httpConnection: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            val inputStream = httpConnection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)
            /**
             * perform threadsafe operation to change the UI. it does some background activities
             * to make it right
             * */
            launch(Dispatchers.Main) {

                Log.d("CoroutineExercise", "Name of thread is ${Thread.currentThread().name}")
                binding.imageView.setImageBitmap(bitmapImage)
                binding.textView.text = imgDesc[index]
            }
        }

    }
}
/*
    private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount
        for(i in 0 until pointerCount) {
            val x = m.getX(i)
            val y = m.getY(i)

            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex
            val actionString: String

            when (action) {

                MotionEvent.ACTION_DOWN -> actionString = "DOWN"
                MotionEvent.ACTION_UP -> actionString = "UP"
                MotionEvent.ACTION_POINTER_UP -> actionString = "PNTR UP"
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "PNTR DOWN"
                MotionEvent.ACTION_MOVE->actionString="MOVE"
                else->actionString = ""
            }
            val touchStatus =
                "Action : $actionString Index $actionIndex ID : $id X: $x Y: $y"

            /*if(id==0)
                binding.textView1.text = touchStatus
            else
                binding.textView2.text = touchStatus*/
        }

    }
}
*/
/**
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View

        binding= ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        Thread( {
            val imageUrl = URL("https://www.nps.gov/yose/planyourvisit/images/20170618_155330.jpg")
            val httpConnection = imageUrl.openConnection() as HttpURLConnection

            httpConnection.doInput = true
            httpConnection.connect()

            val inputStream = httpConnection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)
            runOnUiThread {
                binding.imageView.setImageBitmap(bitmapImage)
            }
        }).start()


    }
} **/