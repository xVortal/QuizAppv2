package com.example.quizappv2

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable

class DBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VER){
    companion object{
        private val DATABASE_VER = 2
        private val DATABASE_NAME = "QUIZAPPV2.db"

        private val TABLE_NAME = "Question"
        private val COL_ID = "Id"
        private val COL_QUESTION = "Question"
        private val COL_ANSWERA = "AnswerA"
        private val COL_ANSWERB = "AnswerB"
        private val COL_ANSWERC = "AnswerC"
        private val COL_ANSWERD = "AnswerD"
        private val COL_CORRECTANSWER = "CorrectAnswerA"
        private val COL_DIFFICULTY = "Difficulty"

        private val USER_TABLE_NAME = "Users"
        private val COL_USER_ID = "UserID"
        private val COL_USER_NAME = "UserName"
        private val COL_BRONZE = "BRONZE"
        private val COL_SILVER = "SILVER"
        private val COL_GOLD = "GOLD"
        private val COL_PLATINUM = "PLATINUM"
        private val COL_DIAMOND = "DIAMOND"
        private val COL_BOSS_HP = "BossHP"

        private val ITEM_TABLE_NAME = "Items"
        private val COL_ITEM_ID = "ItemID"
        private val COL_ITEM_NAME = "ItemName"
        private val COL_ITEM_EFFEKT = "ItemEffekt"

        private val USER_ITEM_TABLE_NAME = "UserItem"
      //  private val COL_USER_ID_USER_ITEM = "UserID"
      //  private val COL_ITEM_ID_USER_ITEM = "ItemID"
        private val COL_ANZAHL_USER_ITEM = "AnzahlItem"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        //db!!.execSQL("SELECT * FROM $TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $ITEM_TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $USER_ITEM_TABLE_NAME")
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_QUESTION TEXT," +
                "$COL_ANSWERA TEXT, $COL_ANSWERB TEXT, $COL_ANSWERC TEXT, $COL_ANSWERD TEXT, $COL_CORRECTANSWER TEXT, $COL_DIFFICULTY TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
        val CREATE_TABLE_QUERY_USER: String = ("CREATE TABLE $USER_TABLE_NAME ($COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_USER_NAME TEXT," +
                "$COL_BRONZE INTEGER, $COL_SILVER INTEGER, $COL_GOLD INTEGER, $COL_PLATINUM INTEGER, $COL_DIAMOND INTEGER, $COL_BOSS_HP INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY_USER)
        val CREATE_TABLE_QUERY_ITEM: String = ("CREATE TABLE $ITEM_TABLE_NAME ($COL_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_ITEM_NAME TEXT," +
                "$COL_ITEM_EFFEKT TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY_ITEM)
        val CREATE_TABLE_QUERY_USER_ITEM: String = ("CREATE TABLE $USER_ITEM_TABLE_NAME ($COL_ITEM_ID INTEGER," +
                "$COL_USER_ID INTEGER, $COL_ANZAHL_USER_ITEM INTEGER, PRIMARY KEY($COL_USER_ID, $COL_ITEM_ID))")
        db!!.execSQL(CREATE_TABLE_QUERY_USER_ITEM)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS $ITEM_TABLE_NAME")
        onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS $USER_ITEM_TABLE_NAME")
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

    private fun addToQuestionList(id: Int, question: String, answera: String, answerb:String, answerc:String, answerd:String, correctanswer:String, difficulty:String){
        val question = Question( id, question, answera, answerb, answerc, answerd, correctanswer, difficulty)
        addQuestions(question)
    }

    fun fillQuestionsTable(){
        addToQuestionList(1, "Mit welcher Funktion startet jedes Programm?", "func(...)",
            "first(...)", "main(...)", "function(...)", "main(...)", "1")

        addToQuestionList(2, "Was unterstützt eine IDE nicht ?", "Das bieten einer Codevervollständigungs", "Syntax-Fehler werden angezeigt", "Semantik-Fehler werden angezeigt  ", "schrittweise Durchlaufen des Programmcodes", "Semantik-Fehler werden angezeigt  ", "1")
        addToQuestionList(3, "Was ist ein Arbeitsschritt ?", "Zuweisung eines Wertes", "eine Funktion", "eine Variable", "eine Methode", "Zuweisung eines Wertes", "1")
        addToQuestionList(4, "Was wird ausgegeben beim Programmcodeschnipsel: println('Hello, world')println('hi') ?","Hello, worldhi","ein Fehler","Hello, world","hi","ein Fehler","1")
        addToQuestionList(5, "Was wird ausgegeben beim Programmcodeschnipsel:var zahl = 4 var text = 'AP' println(zahl + text +'hi')", "4APhi","4AP hi", "4AP", "ein Fehler","ein Fehler", "2")
        addToQuestionList(6, "Was wird ausgegeben beim Programmcodeschnipsel: fun main ( args : Array <String> ) { var inhaltFlasche = 80 var inhaltGlas : Int = 40 val maxGlas = 150 val umfuellMenge = maxGlas - inhaltGlas if ( inhaltFlasche >= umfuellMenge) { inhaltGlas += umfuellMenge inhaltFlasche -= umfuellMenge } else { inhaltGlas += inhaltFlasche inhaltFlasche -= inhaltFlasche } println ('In der Flasche sind noch €inhaltFlasche ml. vorhanden.')" + "}",
            "einen Fehler", "In der Flasche sind noch -30 ml. vorhanden.", "In der Flasche sind noch 0 ml. vorhanden. ", "In der Flasche sind noch 30 ml. vorhanden.", "In der Flasche sind noch 0 ml. vorhanden. ", "2")
        addToQuestionList(7, "Wie wird eine Funktion aufgebaut ?", "Schlüsselwort, Funktionsname, Parameterliste, Rückgabetyp", "Funktionsname, Parameterliste, Schlüsselwort, Rückgabegerät", "Rückgabegerät, Parameterliste, Funktionsname, Schlüsselwort", "Funktionsname, Schlüsselwort, Parameterliste, Rückgabetyp", "Schlüsselwort, Funktionsname, Parameterliste, Rückgabetyp","2")
    }

    private fun addQuestions(question: Question)
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

    fun addUserToUserTable(name: String){
        val db = this.writableDatabase
        val values = ContentValues()
        //values.put(COL_USER_ID, 1)
        values.put(COL_USER_NAME, name)
        values.put(COL_BRONZE, 0)
        values.put(COL_SILVER, 0)
        values.put(COL_GOLD, 0)
        values.put(COL_PLATINUM, 0)
        values.put(COL_DIAMOND, 0)
        values.put(COL_BOSS_HP, 0)

        db.insert(USER_TABLE_NAME, null, values)
    }

    fun deleteDataFromUserTable(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + USER_TABLE_NAME)
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name = '"+ USER_TABLE_NAME +"'")
    }

    fun checkIfUserIsAvailable(): Boolean{
        val db = this.readableDatabase
        var available : Boolean
        available = false
        val selectQuery = "SELECT * FROM $USER_TABLE_NAME WHERE $COL_USER_ID = 1;"
        println(selectQuery)
        val cursor = db.rawQuery(selectQuery,null)
        if (cursor != null && cursor.moveToFirst()){
            available = true
        }
        cursor.close()
        return available
    }

    fun getUserName() : User{
        val db = this.writableDatabase
        val user = User()
        val selectQuery = "SELECT * FROM $USER_TABLE_NAME WHERE $COL_USER_ID = 1"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor!=null && cursor.moveToFirst()){
            user.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID)))
            user.username = cursor.getString(cursor.getColumnIndex(COL_USER_NAME))
            user.bronze = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_BRONZE)))
            user.silver = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_SILVER)))
            user.gold = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_GOLD)))
            user.platinum = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_PLATINUM)))
            user.diamond = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_DIAMOND)))
            user.bosshp = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_BOSS_HP)))
        }
        cursor.close()
        return user
    }

    fun updateCups(cup : String){
        val db = this.writableDatabase
        if(cup.equals("bronze")){

        }
        if(cup.equals("silver")){

        }
        if(cup.equals("gold")){

        }
        if(cup.equals("platinum")){

        }
        if(cup.equals("diamond")){

        }
    }
}