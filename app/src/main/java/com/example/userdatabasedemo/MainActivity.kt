package com.example.userdatabasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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

        userAdapter.setOnClickDeleteItem {
            deleteUser(it.id)
        }
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

    private fun deleteUser(id: Int){

        val profileAlertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        profileAlertDialog.setTitle(R.string.delete)
        profileAlertDialog.setCancelable(true)
        profileAlertDialog.setMessage(R.string.alert_message)
        profileAlertDialog.setPositiveButton(R.string.yes) { dialog, _ ->
            dataBaseHandler.deleteUserById(id)
            getUserData()
            dialog.dismiss()

        }.setNegativeButton(R.string.no) { dialog, _ ->
            dialog.dismiss()
        }
        profileAlertDialog.show()

    }
}