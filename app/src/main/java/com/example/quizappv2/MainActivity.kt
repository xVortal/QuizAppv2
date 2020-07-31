package com.example.quizappv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var lstQuestion:List<Question> = ArrayList<Question>()

    var correct = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        return 4
    }

    private fun defineButtons(){
        val answerABtn = findViewById<Button>(R.id.AnswerABtn)
        answerABtn.setOnClickListener{
            if(answerABtn.getText().equals(correct)){

            }
        }
    }


}
