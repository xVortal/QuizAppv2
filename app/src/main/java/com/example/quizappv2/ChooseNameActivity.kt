package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChooseNameActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosename)
        db = DBHelper(this)
        if(db.checkIfUserIsAvailable()){
            //println(db.getUserName().username)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        db.fillItemTable()
        db.fillUserItemsTable()
        db.cleanDatabase()

        db.fillQuestionsTable()
        defineButton()
    }

    private fun defineButton(){
        val inputName = findViewById<EditText>(R.id.userName)
        //db.deleteDataFromUserTable()
        val enterName = findViewById<Button>(R.id.enterName)
        enterName.setOnClickListener {
            db.addUserToUserTable(inputName.getText().toString())
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("inputName", inputName.getText().toString())
            startActivity(intent)
        }
    }
}