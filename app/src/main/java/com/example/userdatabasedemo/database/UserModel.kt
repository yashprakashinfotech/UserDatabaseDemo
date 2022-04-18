package com.example.userdatabasedemo.database

import java.util.*

data class UserModel(
    var id : Int = getAutoId() ,
    var username : String = "",
    var designation : String = "",
    var userId : String = "",
    var bloodGroup : String = ""
){
    companion object{
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}
//class UserModel {
//
//    var id : Int = 0
//    var username : String = ""
//    var designation : String = ""
//    var userId : String = ""
//    var bloodGroup : String = ""
//
//    constructor(username : String,designation : String,userId : String,bloodGroup : String){
//        this.username = username
//        this.designation = designation
//        this.userId= userId
//        this.bloodGroup = bloodGroup
//    }
//
//    constructor(){}
//}