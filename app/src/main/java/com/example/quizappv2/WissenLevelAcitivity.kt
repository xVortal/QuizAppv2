package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizappv2.R

class WissenLevelAcitivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wissen_level)

        val grundWissenBtn = findViewById<Button>(R.id.GrundwissenBtn)
        grundWissenBtn.setOnClickListener{
            val intent = Intent(this, StartTestActivity::class.java)
            startActivity(intent)
        }

        val erweitertesWissenBtn = findViewById<Button>(R.id.ErweitertesWissenBtn)
        erweitertesWissenBtn.setOnClickListener{
            val intent = Intent(this, StartTestActivity::class.java)
            startActivity(intent)
        }
    }
}
