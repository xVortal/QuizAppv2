package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cups.*

class CupsViewActivity: AppCompatActivity() {

    internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cups)
        db = DBHelper(this)
        defineHomeButton()
        getCupCounts()
    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCupCounts(){
        textCupBronze.setText("Bronze: " + db.getUserName().bronze)
        textCupSilver.setText("Silver: " + db.getUserName().silver)
        textCupGold.setText("Gold: " + db.getUserName().gold)
        textCupPlatinum.setText("Platinum: " + db.getUserName().platinum)
        textCupDiamond.setText("Diamond: " + db.getUserName().diamond)
    }

    private fun setTextView(){
        cupsView.setText(db.getUserName().username + " hier findest du deine errungenen Pokale")
    }
}