package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_items.*

class ItemViewActivity: AppCompatActivity() {

    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        db = DBHelper(this)
        defineHomeButton()
        getItemCounts()
        setTextView()
    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getItemCounts(){
        item1NameText.setText(db.getItemWithId(1).itemname)
        item1EffektText.setText(db.getItemFromUserWithId(1).anzahl.toString())
        item2NameText.setText(db.getItemWithId(2).itemname)
        item2EffektText.setText(db.getItemFromUserWithId(2).anzahl.toString())
        item3NameText.setText(db.getItemWithId(3).itemname)
        item3EffektText.setText(db.getItemFromUserWithId(3).anzahl.toString())
        item4NameText.setText(db.getItemWithId(4).itemname)
        item4EffektText.setText(db.getItemFromUserWithId(4).anzahl.toString())
        item5NameText.setText(db.getItemWithId(5).itemname)
        item5EffektText.setText(db.getItemFromUserWithId(5).anzahl.toString())
        item6NameText.setText(db.getItemWithId(6).itemname)
        item6EffektText.setText(db.getItemFromUserWithId(6).anzahl.toString())
        item7NameText.setText(db.getItemWithId(7).itemname)
        item7EffektText.setText(db.getItemFromUserWithId(7).anzahl.toString())
        item8NameText.setText(db.getItemWithId(8).itemname)
        item8EffektText.setText(db.getItemFromUserWithId(8).anzahl.toString())
        item9NameText.setText(db.getItemWithId(9).itemname)
        item9EffektText.setText(db.getItemFromUserWithId(9).anzahl.toString())
    }

    private fun setTextView(){
        textViewItems.setText(db.getUserName().username + " hier findest du deine gewonnen Items")
    }
}