package com.example.quizappv2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var lstQuestion:List<Question> = ArrayList<Question>()

    private var correct = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        db.cleanDatabase()

        db.fillQuestionsTable()

        if(getIntent().getIntExtra("lvl", 0) == 2) {
            print(getIntent().getIntExtra("lvl", 1))
            putTheQuestionInApp()
        }else{
            putOnlyEasyQuestionInApp()
        }
        defineButtons()
        defineHomeButton()
    }
    
    private fun putTheQuestionInApp(){
        if(intent != null){
            val questions: Question = db.getOneFromAllQuestions()
            QuestionTextView.setText(questions.question)
            AnswerABtn.setText(questions.answera)
            AnswerBBtn.setText(questions.answerb)
            AnswerCBtn.setText(questions.answerc)
            AnswerDBtn.setText(questions.answerd)
            this.correct = questions.correctanswer.toString()
        }
    }

    private fun putOnlyEasyQuestionInApp(){
        if(intent != null){
            val question: Question = db.getEasyQuestion()
            QuestionTextView.setText(question.question)
            AnswerABtn.setText(question.answera)
            AnswerBBtn.setText(question.answerb)
            AnswerCBtn.setText(question.answerc)
            AnswerDBtn.setText(question.answerd)
            this.correct = question.correctanswer.toString()
        }
    }


    private fun getRandomInteger() : Int
    {
        val randomInteger = (1..4).random()
        println(randomInteger)
        return randomInteger
    }

    private fun defineButtons(){
        disableNextButton()
        val answerABtn = findViewById<Button>(R.id.AnswerABtn)
        answerABtn.setOnClickListener{
            if(answerABtn.getText().equals(correct)){
                answerABtn.setBackgroundColor(Color.parseColor("#5cbd28"))
                disableButton()
            } else {
                answerABtn.setBackgroundColor(Color.parseColor("#c91616"))
                showRightAnswer();
            }

        }
        val answerBBtn = findViewById<Button>(R.id.AnswerBBtn)
        answerBBtn.setOnClickListener{
            if(answerBBtn.getText().equals(correct)){
                answerBBtn.setBackgroundColor(Color.parseColor("#5cbd28"))
                disableButton()
            } else {
                answerBBtn.setBackgroundColor(Color.parseColor("#c91616"))
                showRightAnswer();
            }

        }
        val answerCBtn = findViewById<Button>(R.id.AnswerCBtn)
        answerCBtn.setOnClickListener{
            if(answerCBtn.getText().equals(correct)){
                answerCBtn.setBackgroundColor(Color.parseColor("#5cbd28"))
                disableButton()
            } else {
                answerCBtn.setBackgroundColor(Color.parseColor("#c91616"))
                showRightAnswer();
            }

        }
        val answerDBtn = findViewById<Button>(R.id.AnswerDBtn)
        answerDBtn.setOnClickListener{
            if(answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundColor(Color.parseColor("#5cbd28"))
                disableButton()
            }
            if(!answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundColor(Color.parseColor("#c91616"))
                showRightAnswer();
            }

        }

    }

    private fun showRightAnswer(){
        if(AnswerABtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerABtn).setBackgroundColor(Color.parseColor("#5cbd28"))
        }
        if(AnswerBBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerBBtn).setBackgroundColor(Color.parseColor("#5cbd28"))
        }
        if(AnswerCBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerCBtn).setBackgroundColor(Color.parseColor("#5cbd28"))
        }
        if(AnswerDBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerDBtn).setBackgroundColor(Color.parseColor("#5cbd28"))
        }
        disableButton()
    }

    private fun disableButton(){
        findViewById<Button>(R.id.AnswerABtn).isEnabled = false
        findViewById<Button>(R.id.AnswerBBtn).isEnabled = false
        findViewById<Button>(R.id.AnswerCBtn).isEnabled = false
        findViewById<Button>(R.id.AnswerDBtn).isEnabled = false
        enableNextButton()
    }
    private fun disableNextButton(){
        findViewById<Button>(R.id.nextQuestionBtn).isEnabled = false
    }

    private fun enableNextButton(){
        val nextBtn = findViewById<Button>(R.id.nextQuestionBtn)
        nextBtn.isEnabled = true
        nextBtn.setOnClickListener {
            if (getIntent().getIntExtra("lvl", 0) == 2){
                putTheQuestionInApp()
                enableButtons()
            } else {
                putOnlyEasyQuestionInApp()
                enableButtons()
            }
        }
    }

    private fun enableButtons(){
        findViewById<Button>(R.id.AnswerABtn).isEnabled = true
        findViewById<Button>(R.id.AnswerABtn).setBackgroundColor(Color.parseColor("#79A8D3"))
        findViewById<Button>(R.id.AnswerBBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerBBtn).setBackgroundColor(Color.parseColor("#79A8D3"))
        findViewById<Button>(R.id.AnswerCBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerCBtn).setBackgroundColor(Color.parseColor("#79A8D3"))
        findViewById<Button>(R.id.AnswerDBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerDBtn).setBackgroundColor(Color.parseColor("#79A8D3"))
        disableNextButton()
    }

    private fun clearForNextQuestion(){
        enableButtons()
    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, ChooseModusActivity::class.java)
            startActivity(intent)
        }
    }

}
