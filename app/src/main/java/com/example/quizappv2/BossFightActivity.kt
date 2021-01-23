package com.example.quizappv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BossFightActivity: AppCompatActivity() {

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bossfight)
        db = DBHelper(this)
    }
}