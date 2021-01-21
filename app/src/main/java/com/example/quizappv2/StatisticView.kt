package com.example.quizappv2

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_statistic.*
import java.text.DecimalFormat

class StatisticView : AppCompatActivity() {

    internal lateinit var db:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)
        db = DBHelper(this)
        setTextViewProcent()
        defineProgressBar()
        definePassOrNotPassed()
        defineHomeButton()
        setCup()
    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, ChooseModusActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTextViewProcent(){
        val anzahlRightAnswer = findViewById<TextView>(R.id.anzahlRichtig)
        val procentRightAnswer = findViewById<TextView>(R.id.prozentRichtig)
        val anzahlWrongAnswer = findViewById<TextView>(R.id.anzahlFalsch)
        val procentWrongAnswer = findViewById<TextView>(R.id.prozentFalsch)
        anzahlRightAnswer.setText(getRightAnswerCount() + " / 20")
        anzahlWrongAnswer.setText(getWrongAnswerCount() + " / 20")
        procentRightAnswer.setText(getRightAnswerProcent() + "%")
        procentWrongAnswer.setText(getWrongAnswerProcent()+ "%")
    }

    private fun getRightAnswerCount() : String {
        var rightanswercount = 20 - getIntent().getIntExtra("WAC", 0)
        return rightanswercount.toString()
    }

    private fun getWrongAnswerCount() : String {
        var wronganswercount = getIntent().getIntExtra("WAC", 0)
        return wronganswercount.toString()
    }

    private fun getRightAnswerProcent() : String {
        val f = DecimalFormat("#0.00")
        println(getIntent().getIntExtra("WAC", 0).toFloat())

        var rightanswerprocent : Float = (((20 - getIntent().getIntExtra("WAC", 0).toFloat()) / 20) * 100)
        println("richtige Antwort Prozent: " + rightanswerprocent)
        println("richitge Antwort Prozent: " + f.format(rightanswerprocent))
        return f.format(rightanswerprocent)
    }

    private fun getWrongAnswerProcent() : String{
        val f = DecimalFormat("#0.00")
        var rightanswerprocent : Float = (((20 - getIntent().getIntExtra("WAC", 0).toFloat()) / 20) * 100)
        var wronganswerprocent = 100-rightanswerprocent
        println("falsche Antwort Prozent: " + wronganswerprocent)
        return f.format(wronganswerprocent)
    }

    private fun defineProgressBar(){
        val progressBarEnd = findViewById<ProgressBar>(R.id.StatisticBar)
        progressBarEnd.setProgress((20*5) - (getIntent().getIntExtra("WAC", 0)*5))
    }

    private fun definePassOrNotPassed(){
        val passOrNotPassed = findViewById<TextView>(R.id.Status)
        if(getRightAnswerProcent().toFloat() > 80){
            var mp = MediaPlayer.create(this, R.raw.bestanden)
            mp.start()
            passOrNotPassed.setText("bestanden")
        } else {
            passOrNotPassed.setText("nicht bestanden")
        }
    }

    private fun setCup(){
        var procentPoints = getRightAnswerProcent().toFloat()
        if(procentPoints >= 50.0.toFloat() && procentPoints < 70.0.toFloat()){
            imageCup.setImageResource(R.drawable.bronze_cup)
            textCup.setText("Bronze")
            db.updateCups("bronze")
        }
        if(procentPoints >= 70.0.toFloat() && procentPoints < 90.0.toFloat()){
            imageCup.setImageResource(R.drawable.silver_cup)
            textCup.setText("Silver")
            db.updateCups("silver")
        }
        if(procentPoints >= 90.0.toFloat() && procentPoints < 95.0.toFloat()){
            imageCup.setImageResource(R.drawable.gold_cup)
            textCup.setText("Gold")
            db.updateCups("gold")
        }
        if(procentPoints >= 95.0.toFloat() && procentPoints < 100.0.toFloat()){
            imageCup.setImageResource(R.drawable.platinum_cup)
            textCup.setText("Platinum")
            db.updateCups("platinum")
        }
        if(procentPoints == 100.0.toFloat()){
            imageCup.setImageResource(R.drawable.diamond_cup)
            textCup.setText("Diamond")
            db.updateCups("diamond")
        }
    }



}