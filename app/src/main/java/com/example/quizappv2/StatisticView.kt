package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class StatisticView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)
        setTextViewProcent()
        defineProgressBar()
        definePassOrNotPassed()
        defineHomeButton()
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

        var rightanswerprocent : Float = (20 / (20 - getIntent().getIntExtra("WAC", 0).toFloat()))
        println("richtige Antwort Prozent: " + rightanswerprocent)
        return f.format(rightanswerprocent)
    }

    private fun getWrongAnswerProcent() : String{
        val f = DecimalFormat("#0.00")
        var rightanswerprocent : Float = (20 / (20 - getIntent().getIntExtra("WAC", 0).toFloat()))
        var wronganswerprocent = 100-rightanswerprocent
        println("falsche Antwort Prozent: " + wronganswerprocent)
        return f.format(wronganswerprocent)
    }

    private fun defineProgressBar(){
        val progressBarEnd = findViewById<ProgressBar>(R.id.StatisticBar)
        progressBarEnd.setProgress(20 - getIntent().getIntExtra("WAC", 0))
    }

    private fun definePassOrNotPassed(){
        val passOrNotPassed = findViewById<TextView>(R.id.Status)
        if(getRightAnswerProcent().toFloat() > 80){
            passOrNotPassed.setText("bestanden")
        } else {
            passOrNotPassed.setText("nicht bestanden")
        }
    }
}