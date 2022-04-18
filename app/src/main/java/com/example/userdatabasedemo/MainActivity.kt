package com.example.userdatabasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabasedemo.activity.FormActivity
import com.example.userdatabasedemo.adapter.UserAdapter
import com.example.userdatabasedemo.database.*
import com.example.userdatabasedemo.helper.KeyClass
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fabAdd : FloatingActionButton
    private lateinit var userList : RecyclerView
    private lateinit var userAdapter : UserAdapter
    private lateinit var dataBaseHandler : DataBaseHandler

//    lateinit var userNameArray : ArrayList<String>
//    lateinit var designationArray : ArrayList<String>
//    lateinit var userIdArray : ArrayList<String>
//    lateinit var bloodGroupArray : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        getUserData()
        fabAdd.setOnClickListener {
            val i = Intent(this,FormActivity::class.java)
            i.putExtra(KeyClass.KEY_CAME_FROM,true)
            startActivity(i)
        }


//        listDisplay()

//        storeDataInArray()
    }

    private fun initView(){
        fabAdd = findViewById(R.id.fabAdd)
        userList = findViewById(R.id.userList)
        dataBaseHandler = DataBaseHandler(this)
        userAdapter = UserAdapter(this)
    }

    private fun initRecyclerView() {
        userList.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this)
        userList.adapter = userAdapter
    }

    private fun getUserData(){
        val userListData = dataBaseHandler.readAllData()

        // Display Data in recyclerView
        userAdapter.addItem(userListData)
    }

//    private fun storeDataInArray(){
//
//        var cursor : Cursor = dataBaseHandler.readAllData()
//
//        if (cursor.count == 0){
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
//        }
//        else{
//
//            while (cursor.moveToNext()){
//                userNameArray.add(cursor.getString(0))
//                designationArray.add(cursor.getString(1))
//                userIdArray.add(cursor.getString(2))
//                bloodGroupArray.add(cursor.getString(3))
//
//            }
//        }
//    }

//    private fun listDisplay(){
//        userAdapter = UserAdapter(this,DataBaseHandler.list)
//        userList.adapter = userAdapter
//        userList.layoutManager = LinearLayoutManager(this)
//    }
}