package com.example.userdatabasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabasedemo.activity.FormActivity
import com.example.userdatabasedemo.adapter.UserAdapter
import com.example.userdatabasedemo.database.DataBaseHandler
import com.example.userdatabasedemo.database.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fabAdd : FloatingActionButton
    private lateinit var userList : RecyclerView
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd = findViewById(R.id.fabAdd)
        userList = findViewById(R.id.userList)

        fabAdd.setOnClickListener {
            val i = Intent(this,FormActivity::class.java)
            startActivity(i)
        }
        listDisplay()

    }

    private fun listDisplay(){
        userAdapter = UserAdapter(this,DataBaseHandler.list)
        userList.adapter = userAdapter
        userList.layoutManager = LinearLayoutManager(this)
    }
}