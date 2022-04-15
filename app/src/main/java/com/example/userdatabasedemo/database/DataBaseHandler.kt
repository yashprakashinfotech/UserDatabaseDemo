package com.example.userdatabasedemo.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception


//val databaseName = "UserDB"
//val tableName = "Users"
//val colId = "id"
//val colUsername = "username"
//val colDesignation = "designation"
//val colUserId = "userId"
//val colBloodGroup = "bloodGroup"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, databaseName,null, databaseVersion){


    companion object{
//        var list : ArrayList<UserModel> = ArrayList()

        const val databaseVersion = 1
        const val databaseName = "UserDB"
        const val tableName = "Users"
        const val colId = "id"
        const val colUsername = "username"
        const val colDesignation = "designation"
        const val colUserId = "userId"
        const val colBloodGroup = "bloodGroup"

    }
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
        db!!.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    fun insertData(userModel: UserModel) : Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(colUsername,userModel.username)
        cv.put(colDesignation,userModel.designation)
        cv.put(colUserId,userModel.userId)
        cv.put(colBloodGroup,userModel.bloodGroup)
        var result = db.insert(tableName,null,cv)

        db.close()
        return result

//        if (result == (-1).toLong()){
//            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
//        }
    }

//    @SuppressLint("Range")
//    fun readData() : MutableList<UserModel>{
//
//
//        val db = this.readableDatabase
//        val query = "Select * from $tableName"
//        val result = db.rawQuery(query,null)
//
//        if (result.moveToFirst()){
//            do {
//                var user = UserModel()
////                user.username = result.getString(0)
////                user.designation = result.getString(0)
////                user.userId = result.getString(0)
////                user.bloodGroup = result.getString(0)
//                user.username = result.getString(result.getColumnIndex(colUsername))
//                user.designation = result.getString(result.getColumnIndex(colDesignation))
//                user.userId = result.getString(result.getColumnIndex(colUserId))
//                user.bloodGroup = result.getString(result.getColumnIndex(colBloodGroup))
//                list.add(user)
//            }while (result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//
//        return list
//    }


    @SuppressLint("Recycle", "Range")
    fun readAllData(): ArrayList<UserModel> {
        val db = this.readableDatabase
        val query = "Select * from $tableName"

        val userList : ArrayList<UserModel> = ArrayList()

        var cursor : Cursor?

        try {
            cursor = db.rawQuery(query,null)
        }
        catch (e:Exception){
            db.execSQL(query)
            e.printStackTrace()
            return ArrayList()
        }

        var id : Int
        var username : String
        var designation : String
        var userId : String
        var bloodGroup : String


        if (cursor.moveToFirst()){
            do {
//                id = cursor.getInt(cursor.getColumnIndex(colId))
                username = cursor.getString(cursor.getColumnIndex(colUsername))
                designation = cursor.getString(cursor.getColumnIndex(colDesignation))
                userId = cursor.getString(cursor.getColumnIndex(colUserId))
                bloodGroup = cursor.getString(cursor.getColumnIndex(colBloodGroup))

                val users = UserModel(username = username,designation = designation,bloodGroup = bloodGroup)
                userList.add(users)

            }while (cursor.moveToNext())
        }

        return userList

    }

}