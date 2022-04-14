package com.example.userdatabasedemo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val databaseName = "UserDB"
val tableName = "Users"
val colId = "id"
val colUsername = "username"
val colDesignation = "designation"
val colUserId = "userId"
val colBloodGroup = "bloodGroup"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, databaseName,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + tableName +" ("+
                colId + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                colUsername +" " +"VARCHAR(256)," +
                colDesignation+" " + "VARCHAR(256)," +
                colUserId+" " + "VARCHAR(256)," +
                colBloodGroup +" "+ "VARCHAR(256)"+ ")"

        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun insertData(user: User){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(colUsername,user.username)
        cv.put(colDesignation,user.designation)
        cv.put(colUserId,user.userId)
        cv.put(colBloodGroup,user.bloodGroup)
        var result = db.insert(tableName,null,cv)
        if (result == (-1).toLong()){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
        }
    }

}