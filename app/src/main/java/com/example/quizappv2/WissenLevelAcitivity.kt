package com.example.quizappv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizappv2.R

class WissenLevelAcitivity : AppCompatActivity() {

    var lvl = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wissen_level)

        val grundWissenBtn = findViewById<Button>(R.id.GrundwissenBtn)
        grundWissenBtn.setOnClickListener{
            setlvl(1)
            val intent = Intent(this, StartTestActivity::class.java)
            startActivity(intent)
        }

        val erweitertesWissenBtn = findViewById<Button>(R.id.ErweitertesWissenBtn)
        erweitertesWissenBtn.setOnClickListener{
            setlvl(2)
            val intent = Intent(this, StartTestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setlvl(i : Int){
        this.lvl = i
    }

    public fun getlvl() : Int{
        return this.lvl
    }
}
