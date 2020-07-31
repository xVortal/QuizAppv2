package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartTestActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_test_activity)

        val startTestBtn = findViewById<Button>(R.id.StartTestBtn)
        startTestBtn.setOnClickListener{
            startQuiz();
        }
    }

    fun startQuiz (){
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

}