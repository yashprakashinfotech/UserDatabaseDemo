package com.example.userdatabasedemo.database

class User {

//    var id : Int = 0
    var username : String = ""
    var designation : String = ""
    var userId : String = ""
    var bloodGroup : String = ""

    constructor(username : String,designation : String,userId : String,bloodGroup : String){
        this.username = username
        this.designation = designation
        this.userId= userId
        this.bloodGroup = bloodGroup
    }

    constructor(){}
}