package com.example.quizappv2

class Item {
    var id : Int = 0
    var itemname : String ?= null;
    var itemeffekt : String ?= null;
    var itemtier: Int = 0

    constructor(){}

    constructor(id: Int, itemname: String, itemeffekt: String, itemtier: Int)
    {
        this.id = id
        this.itemname = itemname
        this.itemeffekt = itemeffekt
        this.itemtier = itemtier
    }
}