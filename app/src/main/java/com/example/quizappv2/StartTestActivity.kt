package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappv2.R

class StartTestActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_test)

        val startTestBtn = findViewById<Button>(R.id.StartTestBtn)
        startTestBtn.setOnClickListener{
            startQuiz();
        }
    }

    fun startQuiz (){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}