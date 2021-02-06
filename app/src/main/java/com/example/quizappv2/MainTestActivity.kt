package com.example.quizappv2

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_test.*


class MainTestActivity : AppCompatActivity() {
    internal lateinit var db:DBHelper
    internal var lstQuestion:List<Question> = ArrayList<Question>()

    private var correct = ""
    private var questionProgressNumber = 0
    private var wrongAnswerCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        //diese 3 Funktionen für geamification ausgeschaltet
        db = DBHelper(this)
      //  db.cleanDatabase()

      //  db.fillQuestionsTable()


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
            val questions: Question = db.getOneFromAllQuestions()//getOneQuestion(getRandomInteger())
            print(questions.id)
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

    //muss noch auf die maximale Anzahl an Einträgen verbessert werden
    private fun getRandomInteger() : Int
    {
        val randomInteger = (1..4).random()
        println(randomInteger)
        return randomInteger
    }

    private fun defineButtons(){
        disableNextButton()
        var mp = MediaPlayer.create(this, R.raw.richtig)
        val answerABtn = findViewById<Button>(R.id.AnswerABtn)
        answerABtn.setOnClickListener{
            if(answerABtn.getText().equals(correct)){
                answerABtn.setBackgroundResource(R.drawable.rounded_right_answer)
                disableButton()
                mp.start()
            } else {
                answerABtn.setBackgroundResource(R.drawable.rounded_wrong_answer)
                showRightAnswer();
            }
            // answerABtn.isEnabled = false
        }
        val answerBBtn = findViewById<Button>(R.id.AnswerBBtn)
        answerBBtn.setOnClickListener{
            if(answerBBtn.getText().equals(correct)){
                answerBBtn.setBackgroundResource(R.drawable.rounded_right_answer)
                disableButton()
                mp.start()
            } else {
                answerBBtn.setBackgroundResource(R.drawable.rounded_wrong_answer)
                showRightAnswer();
            }
            //  answerBBtn.isEnabled = false
        }
        val answerCBtn = findViewById<Button>(R.id.AnswerCBtn)
        answerCBtn.setOnClickListener{
            if(answerCBtn.getText().equals(correct)){
                answerCBtn.setBackgroundResource(R.drawable.rounded_right_answer)
                disableButton()
                mp.start()
            } else {
                answerCBtn.setBackgroundResource(R.drawable.rounded_wrong_answer)
                showRightAnswer();
            }
            //   answerCBtn.isEnabled = false
        }
        val answerDBtn = findViewById<Button>(R.id.AnswerDBtn)
        answerDBtn.setOnClickListener{
            if(answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundResource(R.drawable.rounded_right_answer)
                disableButton()
                mp.start()
            }
            if(!answerDBtn.getText().equals(correct)){
                answerDBtn.setBackgroundResource(R.drawable.rounded_wrong_answer)
                showRightAnswer();
            }
            //    answerDBtn.isEnabled = false
        }

    }

    private fun showRightAnswer(){
        if(AnswerABtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerABtn).setBackgroundResource(R.drawable.rounded_right_answer)
        }
        if(AnswerBBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerBBtn).setBackgroundResource(R.drawable.rounded_right_answer)
        }
        if(AnswerCBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerCBtn).setBackgroundResource(R.drawable.rounded_right_answer)
        }
        if(AnswerDBtn.getText().equals(correct)){
            findViewById<Button>(R.id.AnswerDBtn).setBackgroundResource(R.drawable.rounded_right_answer)
        }
        setWrongAnswerCount(getWrongAnswerCount()+1)
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
            if (getIntent().getIntExtra("lvl", 0) == 2){
                raiseTheProgessBar()
                putTheQuestionInApp()
                enableButtons()
            } else {
                raiseTheProgessBar()
                putOnlyEasyQuestionInApp()
                enableButtons()
            }
        }
    }

    private fun enableButtons(){
        findViewById<Button>(R.id.AnswerABtn).isEnabled = true
        findViewById<Button>(R.id.AnswerABtn).setBackgroundResource(R.drawable.rounded_button_corners)
        findViewById<Button>(R.id.AnswerBBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerBBtn).setBackgroundResource(R.drawable.rounded_button_corners)
        findViewById<Button>(R.id.AnswerCBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerCBtn).setBackgroundResource(R.drawable.rounded_button_corners)
        findViewById<Button>(R.id.AnswerDBtn).isEnabled = true
        findViewById<Button>(R.id.AnswerDBtn).setBackgroundResource(R.drawable.rounded_button_corners)
        disableNextButton()
    }

    fun raiseTheProgessBar(){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var progessraised = getQuestionProgessNumber() + 5
        setQuestionProgressNumber(progessraised)
        progressBar.setProgress(getQuestionProgessNumber())
        println("Return upper Limit of PB Max "+ progressBar.getMax())
        println("Get The Current Process of PB " + progressBar.getProgress())
        if(progressBar.getMax() == getQuestionProgessNumber()){
            setQuestionProgressNumber(0)
            progressBar.setProgress(getQuestionProgessNumber())
            val intent = Intent(this, StatisticView::class.java)
            intent.putExtra("WAC", getWrongAnswerCount())
            startActivity(intent)
        }

    }

    private fun defineHomeButton(){
        val homebutton = findViewById<ImageButton>(R.id.home)
        homebutton.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearForNextQuestion(){
        enableButtons()
    }

    fun getQuestionProgessNumber() : Int{
        return this.questionProgressNumber
    }

    private fun setQuestionProgressNumber(number : Int){
        this. questionProgressNumber = number
    }

    private fun setWrongAnswerCount(i : Int){
        this.wrongAnswerCount = i
    }

    private fun getWrongAnswerCount() : Int{
        return this.wrongAnswerCount
    }
}