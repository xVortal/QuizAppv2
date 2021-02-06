package com.example.quizappv2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class ChooseNameActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosename)
        db = DBHelper(this)
        if(db.checkIfUserIsAvailable()){
            //println(db.getUserName().username)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        } else {

            db.fillItemTable()
            db.fillUserItemsTable()
            db.cleanDatabase()

            db.fillQuestionsTable()
            defineButton()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun defineButton(){
        val inputName = findViewById<EditText>(R.id.userName)
        //db.deleteDataFromUserTable()
        val enterName = findViewById<Button>(R.id.enterName)
        enterName.setOnClickListener {
            db.addUserToUserTable(inputName.getText().toString())
            setBossHPforSemesterStart()
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("inputName", inputName.getText().toString())
            startActivity(intent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setBossHPforSemesterStart(){
        val stringOfSemesterstart = "2021-01-20"
        val dateOfSemesterstart = LocalDate.parse(stringOfSemesterstart, DateTimeFormatter.ISO_DATE)
        val currentDate = LocalDate.now()
        val difference = Period.between(dateOfSemesterstart, currentDate).days
        db.updateBossHP(difference*100)
    }
}