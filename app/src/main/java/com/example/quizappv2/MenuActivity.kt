package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        db = DBHelper(this)
        db.cleanDatabase()

        db.fillQuestionsTable()
        defineButtons()
    }

    private fun defineButtons() {

        val quizModusBtn = findViewById<Button>(R.id.Quiz)
        quizModusBtn.setOnClickListener {
            val intent = Intent(this, ChooseModusActivity::class.java)
            startActivity(intent)
        }

        val cupsBtn = findViewById<Button>(R.id.Cups)
        cupsBtn.setOnClickListener {
            val intent = Intent(this, CupsViewActivity::class.java)
            startActivity(intent)
        }

        val bossBtn = findViewById<Button>(R.id.Boss)
        bossBtn.setOnClickListener{
            val intent = Intent(this, ChooseModusActivity::class.java)
            startActivity(intent)
        }

        val menuText = findViewById<TextView>(R.id.textView)
        menuText.setText("Hallo "+ db.getUserName().username + "\n Was möchtest du tun ?")
    }


}
