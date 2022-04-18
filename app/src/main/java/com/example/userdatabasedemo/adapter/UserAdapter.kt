package com.example.userdatabasedemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabasedemo.R
import com.example.userdatabasedemo.activity.FormActivity
import com.example.userdatabasedemo.database.UserModel
import com.example.userdatabasedemo.helper.KeyClass

class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList : ArrayList<UserModel> = ArrayList()
    private var onClickDeleteItem : ((UserModel)-> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: ArrayList<UserModel>){
        this.userList = item
        notifyDataSetChanged()
    }

    fun setOnClickDeleteItem(callback : (UserModel)->Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.user_view,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]
        holder.bindView(user)
        holder.icDelete.setOnClickListener { onClickDeleteItem!!.invoke(user)}
        holder.llUserView.setOnClickListener {
            val i = Intent(context,FormActivity::class.java)
            i.putExtra(KeyClass.KEY_ID,user.id)
            i.putExtra(KeyClass.KEY_USERNAME,user.username)
            i.putExtra(KeyClass.KEY_DESIGNATION,user.designation)
            i.putExtra(KeyClass.KEY_USER_ID,user.userId)
            i.putExtra(KeyClass.KEY_BLOOD_GROUP,user.bloodGroup)
            context.startActivity(i)
        }



//        holder.userName.text = user.username
//        holder.userDesignation.text = user.designation
//        holder.userId.text = user.userId
//        holder.bloodGroup.text = user.bloodGroup
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var userName : TextView = itemView.findViewById(R.id.userName)
        private var userDesignation : TextView = itemView.findViewById(R.id.userDesignation)
        private var userId : TextView = itemView.findViewById(R.id.userId)
        private var bloodGroup : TextView = itemView.findViewById(R.id.bloodGroup)
        var icDelete : ImageView = itemView.findViewById(R.id.icDelete)
        var llUserView : LinearLayout = itemView.findViewById(R.id.llUserView)

        fun bindView(user : UserModel){
            userName.text = user.username
            userDesignation.text = user.designation
            userId.text = user.userId
            bloodGroup.text = user.bloodGroup
        }
    }
}