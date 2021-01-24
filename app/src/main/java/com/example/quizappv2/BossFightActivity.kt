package com.example.quizappv2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_test.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

class BossFightActivity: AppCompatActivity() {

    internal lateinit var db:DBHelper
    var counter = 0
    var bossHP = 0
    var userHP = 0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bossfight)
        db = DBHelper(this)
        setBossHP()
        setUserHP()
        defineNextButton()
        defineHomeButton()
    }

    private fun getHealthFromUser() : Int{
        var health = db.getItemFromUserWithId(1).anzahl * 10
        health = (db.getItemFromUserWithId(2).anzahl * 20) + health
        health = (db.getItemFromUserWithId(3).anzahl * 30) + health
        //standard health
        health = 100 + health
        return health
    }

    private fun getProtectionFromItems() : Int{
        var armor = db.getItemFromUserWithId(4).anzahl * 10
        armor = (db.getItemFromUserWithId(5).anzahl * 20) + armor
        armor = (db.getItemFromUserWithId(6).anzahl * 30) + armor
        return armor
    }

    private fun getDamageFromUser() : Int{
        var damage = db.getItemFromUserWithId(7).anzahl * 2
        damage = (db.getItemFromUserWithId(8).anzahl * 4) + damage
        damage = (db.getItemFromUserWithId(9).anzahl * 6) + damage
        //Standard damage
        damage + 4
        return damage
    }

    private fun getDamageFromBoss(): Int{
        var damageFromBoss = 50 - getProtectionFromItems()
        return damageFromBoss
    }

    private fun setBossHP(){
        val progressBarBoss = findViewById<ProgressBar>(R.id.bossHP)
        var bossHP = db.getUserName().bosshp
        setbossHP(bossHP)
        progressBarBoss.setMax(bossHP)
        progressBarBoss.setProgress(bossHP)
    }

    private fun setUserHP(){
        val progressBarUser = findViewById<ProgressBar>(R.id.userHP)
        var userHP = getHealthFromUser()
        setuserHP(userHP)
        progressBarUser.setMax(userHP)
        progressBarUser.setProgress(userHP)
    }

    private fun defineNextButton(){
        val nextStepBtn = findViewById<Button>(R.id.nextStep)
        nextStepBtn.setOnClickListener{
            if(getuserHP() > 0 && getbossHP() > 0) {
                if (getCounterForRounds() % 2 == 0) {
                    setBossHPAfterDamage()
                    counter++
                } else {
                    setUserHPAfterDamage()
                    counter++
                }
            } else {
                db.updateBossHP(getbossHP())
            }
        }
    }

    private fun setBossHPAfterDamage(){
        var bossHPAfterDamage = getbossHP() - getDamageFromUser()
        if(bossHPAfterDamage <= 0){
            bossHPAfterDamage = 0
            val nextStepBtn = findViewById<Button>(R.id.nextStep)
            nextStepBtn.isEnabled = false
        }
        setbossHP(bossHPAfterDamage)
        val progressBarBoss = findViewById<ProgressBar>(R.id.bossHP)
        progressBarBoss.setProgress(bossHPAfterDamage)
    }

    private fun setUserHPAfterDamage(){
        var userHPAfterDamage = getuserHP() - getDamageFromBoss()
        if(getDamageFromBoss() < getProtectionFromItems()){
            userHPAfterDamage = getuserHP() - 1
        }
        if(userHPAfterDamage <= 0){
            userHPAfterDamage = 0
            val nextStepBtn = findViewById<Button>(R.id.nextStep)
            nextStepBtn.isEnabled = false
        }
        setuserHP(userHPAfterDamage)
        val progressBarUser = findViewById<ProgressBar>(R.id.userHP)
        progressBarUser.setProgress(userHPAfterDamage)

    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setCounterForRounds(i: Int){
        this.counter = i
    }

    private fun getCounterForRounds(): Int{
        return this.counter
    }

    private fun setbossHP(i: Int){
        this.bossHP = i
    }

    private fun getbossHP(): Int{
        return this.bossHP
    }

    private fun setuserHP(i: Int){
        this.userHP = i
    }

    private fun getuserHP(): Int{
        return this.userHP
    }

}