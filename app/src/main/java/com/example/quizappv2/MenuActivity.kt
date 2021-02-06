package com.example.quizappv2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class MenuActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        db = DBHelper(this)
        increaseBossHP()
        db.updateLastLogin()
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

        val itemBtn = findViewById<Button>(R.id.ItemView)
        itemBtn.setOnClickListener{
            val intent = Intent(this, ItemViewActivity::class.java)
            startActivity(intent)
        }

        val bossBtn = findViewById<Button>(R.id.Boss)
        bossBtn.setOnClickListener{
            val intent = Intent(this, BossFightActivity::class.java)
            startActivity(intent)
        }

        val menuText = findViewById<TextView>(R.id.textView)
        menuText.setText("Hallo "+ db.getUserName().username + "\n Was möchtest du tun ?")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getLastLoginOfUser() : LocalDate {
        var lastlogin = db.getUserName().last_login
        var lldate = LocalDate.parse(lastlogin, DateTimeFormatter.ISO_DATE)
        println("LastLoginDate: "+lldate)
        return lldate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun increaseBossHP(){
        var currentDate = LocalDate.now()
        //var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        var difference = Period.between(getLastLoginOfUser(), currentDate).days
        println("currendate ="+currentDate)
        println("Differenz:" + difference)
        db.updateBossHP(db.getUserName().bosshp + (difference * 100))
    }


}
