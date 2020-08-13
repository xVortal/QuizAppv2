package com.example.quizappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChooseModusActivity : AppCompatActivity() {

    var modus = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modus)

        val testModusBtn = findViewById<Button>(R.id.Testmodusbtn)
        testModusBtn.setOnClickListener{
            setmodus("test")
            val intent = Intent(this, WissenLevelAcitivity::class.java)
            startActivity(intent)
        }

        val lernModusBtn = findViewById<Button>(R.id.Lernmodusbtn)
        lernModusBtn.setOnClickListener{
            setmodus("lern")
            val intent = Intent(this, WissenLevelAcitivity::class.java)
            startActivity(intent)
        }
    }

    private fun setmodus(modi : String){
        this.modus = modi
    }

    public fun getmodus() : String{
        return modus
    }

}