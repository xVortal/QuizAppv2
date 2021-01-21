package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChooseModusActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modus)
        db = DBHelper(this)
        db.cleanDatabase()

        db.fillQuestionsTable()
        defineButtons()
    }

    private fun defineButtons() {

        val testModusBtn = findViewById<Button>(R.id.Testmodusbtn)
        testModusBtn.setOnClickListener {
            val intent = Intent(this, WissenLevelAcitivity::class.java)
            intent.putExtra("modi", "test")
            startActivity(intent)
        }

        val lernModusBtn = findViewById<Button>(R.id.Lernmodusbtn)
        lernModusBtn.setOnClickListener {
            val intent = Intent(this, WissenLevelAcitivity::class.java)
            intent.putExtra("modi", "lern")
            startActivity(intent)
        }
    }


}