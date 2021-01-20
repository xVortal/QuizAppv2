package com.example.quizappv2

class UserItem {
    var itemid : Int = 0
    var userid : Int = 0
    var anzahl: Int = 0

    constructor(){}

    constructor(itemid: Int, userid: Int, anzahl: Int)
    {
        this.itemid = itemid
        this.userid = userid
        this.anzahl = anzahl
    }
}