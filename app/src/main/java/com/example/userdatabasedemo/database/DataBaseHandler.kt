package com.example.userdatabasedemo.database

import android.annotation.SuppressLint
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

    @SuppressLint("Range")
    fun readData() : MutableList<User>{

        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $tableName"
        val result = db.rawQuery(query,null)

        if (result.moveToFirst()){
            do {
                var user = User()
//                user.username = result.getString(0)
//                user.designation = result.getString(0)
//                user.userId = result.getString(0)
//                user.bloodGroup = result.getString(0)
                user.username = result.getString(result.getColumnIndex(colUsername))
                user.designation = result.getString(result.getColumnIndex(colDesignation))
                user.userId = result.getString(result.getColumnIndex(colUserId))
                user.bloodGroup = result.getString(result.getColumnIndex(colBloodGroup))
                list.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

}