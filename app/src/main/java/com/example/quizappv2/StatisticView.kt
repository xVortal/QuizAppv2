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
        setBossHPAfterQuiz()
    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
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
        if(getRightAnswerProcent().toFloat() >= 50){
            var mp = MediaPlayer.create(this, R.raw.bestanden)
            mp.start()
            passOrNotPassed.setText("passed")
        } else {
            passOrNotPassed.setText("not passed")
        }
    }

    private fun setCup(){
        var procentPoints = getRightAnswerProcent().toFloat()
        var cup = ""
        var tier = 0
        if(procentPoints >= 50.0.toFloat() && procentPoints < 70.0.toFloat()){
            cup = "bronze"
            imageCup.setImageResource(R.drawable.bronze_cup)
            textCup.setText("Bronze")
        }
        if(procentPoints >= 70.0.toFloat() && procentPoints < 90.0.toFloat()){
            cup = "silver"
            imageCup.setImageResource(R.drawable.silver_cup)
            textCup.setText("Silver")
        }
        if(procentPoints >= 90.0.toFloat() && procentPoints < 95.0.toFloat()){
            cup = "gold"
            imageCup.setImageResource(R.drawable.gold_cup)
            textCup.setText("Gold")
        }
        if(procentPoints >= 95.0.toFloat() && procentPoints < 100.0.toFloat()){
            cup = "platinum"
            imageCup.setImageResource(R.drawable.platinum_cup)
            textCup.setText("Platinum")
        }
        if(procentPoints == 100.0.toFloat()){
            cup = "diamond"
            imageCup.setImageResource(R.drawable.diamond_cup)
            textCup.setText("Diamond")
        }
        tier = getRandomItemTierForCup(cup)
        if(tier != 0){
            db.updateCups(cup)
            setItemPicture(db.getRandomItemFromTier(tier).id)
        }
    }

    fun setItemPicture(id : Int){
        if(id >= 1 && id <= 9){
            itemsWonText.setText("Item you won:")
        }
        if(id == 1){
            itemwon.setImageResource(R.drawable.vitality_syringe)
        }
        if(id == 2){
            itemwon.setImageResource(R.drawable.vitality_helmet)
        }
        if(id == 3){
            itemwon.setImageResource(R.drawable.vitality_armor)
        }
        if(id == 4){
            itemwon.setImageResource(R.drawable.wood_shield)
        }
        if(id == 5){
            itemwon.setImageResource(R.drawable.protection_helmet)
        }
        if(id == 6){
            itemwon.setImageResource(R.drawable.thorn_armor)
        }
        if(id == 7){
            itemwon.setImageResource(R.drawable.wood_sword)
        }
        if(id == 8){
            itemwon.setImageResource(R.drawable.iron_sword)
        }
        if(id == 9){
            itemwon.setImageResource(R.drawable.gold_sword)
        }
        db.setItemCountForUserWithEndOfTest(id)
    }

    private fun setBossHPAfterQuiz(){
        var difference = (getRightAnswerCount().toInt()*4) - (getWrongAnswerCount().toInt()*2)
        var newbosshp = db.getUserName().bosshp - difference
        db.updateBossHP(newbosshp)
    }

    private fun getRandomItemTierForCup(cup : String) : Int {
        var rnds = (0..1).random()
        if (cup.equals("bronze")) {
            return 1
        }
        if (cup.equals("silver")) {
            if (rnds == 1) {
                return 1
            } else {
                return 2
            }
        }
        if (cup.equals("gold")) {
            return 2
        }
        if (cup.equals("platinum")) {
            if (rnds == 1) {
                return 2
            } else {
                return 3
            }
        }
        if (cup.equals("diamond")) {
            return 3
        }
        return 0
    }
}