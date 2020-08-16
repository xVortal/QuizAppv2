package com.example.quizappv2

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VER){
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "QUIZAPPV2.db"

        private val TABLE_NAME = "Qusetion"
        private val COL_ID = "Id"
        private val COL_QUESTION = "Question"
        private val COL_ANSWERA = "AnswerA"
        private val COL_ANSWERB = "AnswerB"
        private val COL_ANSWERC = "AnswerC"
        private val COL_ANSWERD = "AnswerD"
        private val COL_CORRECTANSWER = "CorrectAnswerA"
        private val COL_DIFFICULTY = "Difficulty"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //db!!.execSQL("SELECT * FROM $TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_QUESTION TEXT," +
                "$COL_ANSWERA TEXT, $COL_ANSWERB TEXT, $COL_ANSWERC TEXT, $COL_ANSWERD TEXT, $COL_CORRECTANSWER TEXT, $COL_DIFFICULTY TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }


    val allQuestion:List<Question>
        get(){
            val lstQuestion = ArrayList<Question>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do {
                    val question = Question()
                    question.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    question.question = cursor.getString(cursor.getColumnIndex(COL_QUESTION))
                    question.answera = cursor.getString(cursor.getColumnIndex(COL_ANSWERA))
                    question.answerb = cursor.getString(cursor.getColumnIndex(COL_ANSWERB))
                    question.answerc = cursor.getString(cursor.getColumnIndex(COL_ANSWERC))
                    question.answerd = cursor.getString(cursor.getColumnIndex(COL_ANSWERD))
                    question.correctanswer = cursor.getString(cursor.getColumnIndex(COL_CORRECTANSWER))
                    question.difficulty = cursor.getString(cursor.getColumnIndex(COL_DIFFICULTY))

                    lstQuestion.add(question)
                } while (cursor.moveToNext())
            }
            db.close()
            return lstQuestion
        }

    fun addQuestions(question: Question)
    {
        val db = this.writableDatabase
        val values = ContentValues()
       // values.put(COL_ID, question.id)
        values.put(COL_QUESTION, question.question)
        values.put(COL_ANSWERA, question.answera)
        values.put(COL_ANSWERB, question.answerb)
        values.put(COL_ANSWERC, question.answerc)
        values.put(COL_ANSWERD, question.answerd)
        values.put(COL_CORRECTANSWER, question.correctanswer)
        values.put(COL_DIFFICULTY, question.difficulty)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getOneFromAllQuestions() : Question {
        val db = this.writableDatabase
        var count = DatabaseUtils.queryNumEntries(db, TABLE_NAME)
        val randomInteger = (1..count.toInt()).random()
        return getOneQuestion(randomInteger)
    }

    private fun  getOneQuestion(id : Int) : Question
    {
        val db = this.writableDatabase
        val questions = Question()
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COL_ID = $id"
        val cursor = db.rawQuery(selectQuery,null)
        if(cursor != null){
            cursor.moveToFirst()
            //while(cursor.moveToNext()){
                questions.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID)))
                questions.question = cursor.getString(cursor.getColumnIndex(COL_QUESTION))
                questions.answera = cursor.getString(cursor.getColumnIndex(COL_ANSWERA))
                questions.answerb = cursor.getString(cursor.getColumnIndex(COL_ANSWERB))
                questions.answerc = cursor.getString(cursor.getColumnIndex(COL_ANSWERC))
                questions.answerd = cursor.getString(cursor.getColumnIndex(COL_ANSWERD))
                questions.correctanswer = cursor.getString(cursor.getColumnIndex(COL_CORRECTANSWER))
                questions.difficulty = cursor.getString(cursor.getColumnIndex(COL_DIFFICULTY))

            //}
        }
        cursor.close()
        return questions
    }

    fun getEasyQuestion() : Question {
        val db = this.writableDatabase
       // val question = Question()
        var list = mutableListOf<Int>()
        var randomInteger= 0
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $COL_DIFFICULTY = 1"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            cursor.moveToFirst()
            while(cursor.moveToNext()){
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))))
            }
            randomInteger = list.random()
        }
        cursor.close()
        println(randomInteger)
        return getOneQuestion(randomInteger)

    }

    fun cleanDatabase(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME)
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name = '"+ TABLE_NAME +"'")

    }
}