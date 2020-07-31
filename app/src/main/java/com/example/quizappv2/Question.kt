package com.example.quizappv2

class Question {
    var id : Int = 0
    var question : String ?= null;
    var answera : String ?= null;
    var answerb : String ?= null;
    var answerc : String ?= null;
    var answerd : String ?= null;
    var correctanswer : String ?= null;
    var difficulty : String ?= null;

    constructor(){}

    constructor(id: Int, question: String, answera: String, answerb: String, answerc: String, answerd: String, correctanswer:String, difficulty: String)
    {
        this.id = id
        this.question = question
        this.answera = answera
        this.answerb = answerb
        this.answerc = answerc
        this.answerd = answerd
        this.correctanswer = correctanswer
        this.difficulty = difficulty
    }
}