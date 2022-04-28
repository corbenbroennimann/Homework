package com.android.example.cookieclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var currentScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgCookie: ImageView = findViewById(R.id.imgCookie)
        val lblTotal: TextView = findViewById(R.id.lblTotal)
        val usrName: EditText = findViewById(R.id.usrName)
        val scoreBoard: TextView = findViewById(R.id.score_board)
        val submitButton: Button = findViewById(R.id.submit_button)

        lblTotal.text = "$currentScore"

        imgCookie.setOnClickListener {
            currentScore ++
            lblTotal.text = "$currentScore"
            Toast.makeText(this, "ouch", Toast.LENGTH_SHORT).show()
        }

        submitButton.setOnClickListener {
            scoreBoard.text = "Score: ${usrName.text} [$currentScore]"
        }
    }
}