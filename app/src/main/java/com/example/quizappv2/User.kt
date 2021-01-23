package com.example.quizappv2

class User {
    var id : Int = 0
    var username : String ?= null;
    var bronze : Int = 0
    var silver : Int = 0
    var gold : Int = 0
    var platinum : Int = 0
    var diamond : Int = 0
    var bosshp : Int = 0
    var last_login : String ?= null

    constructor(){}

    constructor(id: Int, username: String, bronze: Int, silver: Int, gold: Int, platinum: Int, diamond: Int, bosshp: Int, last_login: String)
    {
        this.id = id
        this.username = username
        this.bronze = bronze
        this.silver = silver
        this.gold = gold
        this.platinum = platinum
        this.diamond = diamond
        this.bosshp = bosshp
        this.last_login = last_login
    }
}