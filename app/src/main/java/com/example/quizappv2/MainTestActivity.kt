package com.example.quizappv2

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_test.*


class MainTestActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var lstQuestion:List<Question> = ArrayList<Question>()

    private var correct = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        db = DBHelper(this)
        db.cleanDatabase()

        fillQuestionsTable()


        putTheQuestionInApp()

        defineButtons()
    }



    private fun addToQuestionList(id: Int, question: String, answera: String, answerb:String, answerc:String, answerd:String, correctanswer:String, difficulty:String){
        val question = Question( id, question, answera, answerb, answerc, answerd, correctanswer, difficulty)
        db.addQuestions(question)
    }

    private fun fillQuestionsTable(){
        addToQuestionList(1, "Mit welcher Funktion startet jedes Programm?", "func(...)", "first(...)", "main(...)", "function(...)", "main(...)", "1")
        addToQuestionList(2, "Was ünterstützt eine IDE nicht ?", "Das bieten einer Codevervollständigungs", "Syntax-Fehler werden angezeigt", "Semantik-Fehler werden angezeigt  ", "schrittweise Durchlaufen des Programmcodes", "Semantik-Fehler werden angezeigt  ", "1")
        addToQuestionList(3, "Was ist ein Arbeitsschritt ?", "Zuweisung eines Wertes", "eine Funktion", "eine Variable", "eine Methode", "Zuweisung eines Wertes", "1")
        addToQuestionList(4, "Was wird ausgegeben beim Programmcodeschnipsel: println('Hello, world')println('hi') ?","Hello, worldhi","ein Fehler","Hello, world","hi","ein Fehler","1")
    }

    private fun putTheQuestionInApp(){
        if(intent != null){
            val questions: Question = db!!.getOneQuestion(getRandomInteger())
            print(questions.id)
            QuestionTextView.setText(questions.question)
            AnswerABtn.setText(questions.answera)
            AnswerBBtn.setText(questions.answerb)
            AnswerCBtn.setText(questions.answerc)
            AnswerDBtn.setText(questions.answerd)
            this.correct = questions.correctanswer.toString()
        }
    }

    //muss noch auf die maximale Anzahl an Einträgen verbessert werden
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
                answerABtn.setBackgroundColor(Color.GREEN)
                disableButton()
            } else {
                answerABtn.setBackgroundColor(Color.RED)
                showRightAnswer();
            }
            // answerABtn.isEnabled = false
        }
        val answerBBtn = findViewById<Button>(R.id.AnswerBBtn)
        answerBBtn.setOnClickListener{
            if(answerBBtn.getText().equals(correct)){
                answerBBtn.setBackgroundColor(Color.GREEN)
                disableButton()
            } else {
                answerBBtn.setBackgroundColor(Color.RED)
                showRightAnswer();
            }
            //  answerBBtn.isEnabled = false
        }
        val answerCBtn = findViewById<Button>(R.id.AnswerCBtn)
        answerCBtn.setOnClickListener{
            if(answerCBtn.getText().equals(correct)){
                answerCBtn.setBackgroundColor(Color.GREEN)
                disableButton()
            } else {
                answerCBtn.setBackgroundColor(Color.RED)
                showRightAnswer();
            }
            //   answerCBtn.isEnabled = false
        }
        val answerDBtn = findViewById<Button>(R.id.AnswerDBtn)
        answerDBtn.setOnClickListener{
            if(answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundColor(Color.GREEN)
                disableButton()
            }
            if(!answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundColor(Color.RED)
                showRightAnswer();
            }
            //    answerDBtn.isEnabled = false
        }

    }

    private fun showRightAnswer(){
        if(AnswerABtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerABtn).setBackgroundColor(Color.GREEN)
        }
        if(AnswerBBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerBBtn).setBackgroundColor(Color.GREEN)
        }
        if(AnswerCBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerCBtn).setBackgroundColor(Color.GREEN)
        }
        if(AnswerDBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerDBtn).setBackgroundColor(Color.GREEN)
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
        nextBtn.setOnClickListener{
            putTheQuestionInApp()
            enableButtons()
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
}