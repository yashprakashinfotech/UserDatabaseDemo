package com.example.userdatabasedemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.userdatabasedemo.MainActivity
import com.example.userdatabasedemo.R
import com.example.userdatabasedemo.database.DataBaseHandler
import com.example.userdatabasedemo.database.UserModel
import com.example.userdatabasedemo.helper.KeyClass

class FormActivity : AppCompatActivity() {

    private lateinit var etUsername : EditText
    private lateinit var etDesignation : EditText
    private lateinit var etUserId : EditText
    private lateinit var etBloodGroup : EditText
    private lateinit var btnUpdate : Button
    private lateinit var btnSubmit : Button
    private lateinit var imgEdit : ImageView
    private lateinit var imgBack : ImageView
    private lateinit var txtList : TextView

    private var isCameFrom = false

    private lateinit var dataBaseHandler: DataBaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        initView()
        dataBaseHandler = DataBaseHandler(this)

        imgBack.setOnClickListener {
            onBackPressed()
        }


        val bundle = intent.extras
        isCameFrom = bundle!!.getBoolean(KeyClass.KEY_CAME_FROM)

        if (isCameFrom){
            setAllFieldEnable()
            imgEdit.visibility = View.GONE
            btnUpdate.visibility = View.GONE

            btnSubmit.setOnClickListener {
                addUser()
            }
        }

        else{
            val bundle : Bundle? = intent.extras!!
            val userNameRV = bundle!!.getString(KeyClass.KEY_USERNAME)
            val designationRV = bundle.getString(KeyClass.KEY_DESIGNATION)
            val userIdRV = bundle.getString(KeyClass.KEY_USER_ID)
            val bloodGroupRV = bundle.getString(KeyClass.KEY_BLOOD_GROUP)

            etUsername.setText(userNameRV)
            etDesignation.setText(designationRV)
            etUserId.setText(userIdRV)
            etBloodGroup.setText(bloodGroupRV)

            btnSubmit.visibility = View.GONE
            imgEdit.setOnClickListener {
                setAllFieldEnable()

            }
        }
//        btnUpdate.setOnClickListener {
//            val data = dataBaseHandler.readData()
//            txtList.text = ""
//
//            for (i in 0 until (data.size)){
//                txtList.append(data[i].username + " " + data[i].designation + " " + data[i].userId + " " + data[i].bloodGroup + "\n" )
//            }
//
//        }

    }

    private fun setAllFieldEnable(){

        etUsername.isEnabled = true
        etDesignation.isEnabled = true
        etUserId.isEnabled = true
        etBloodGroup.isEnabled = true
        etBloodGroup.isEnabled = true
        btnUpdate.isEnabled = true

    }

    private fun initView(){
        etUsername = findViewById(R.id.etUserName)
        etDesignation = findViewById(R.id.etDesignation)
        etUserId = findViewById(R.id.etUserId)
        etBloodGroup = findViewById(R.id.etBloodGroup)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnSubmit = findViewById(R.id.btnSubmit)
        imgEdit = findViewById(R.id.imgEdit)
        imgBack = findViewById(R.id.imgBack)
        txtList = findViewById(R.id.txtList)
    }

    private fun addUser(){
//        var id = 0
        var username = etUsername.text.toString()
        var designation = etDesignation.text.toString()
        var userId = etUserId.text.toString()
        var bloodGroup = etBloodGroup.text.toString()

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(designation) && !TextUtils.isEmpty(userId) && !TextUtils.isEmpty(bloodGroup)){

            var user = UserModel(username,designation,userId,bloodGroup)

            val status =dataBaseHandler.insertData(user)
            if (status > -1){
                Toast.makeText(this,"User Added", Toast.LENGTH_SHORT).show()
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)
                clearEditText()
            }
            else{
                Toast.makeText(this,"User Not Added", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this,"Please Fill All Data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearEditText() {
        etUsername.setText("")
        etDesignation.setText("")
        etUserId.setText("")
        etBloodGroup.setText("")
    }

}