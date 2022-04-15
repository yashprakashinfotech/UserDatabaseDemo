package com.example.userdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabasedemo.R
import com.example.userdatabasedemo.database.UserModel

//class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
//class UserAdapter(val context: Context, var userList : ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList : ArrayList<UserModel> = ArrayList()
    fun addItem(item: ArrayList<UserModel>){
        this.userList = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.user_view,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]
        holder.bindView(user)
//        holder.userName.text = user.username
//        holder.userDesignation.text = user.designation
//        holder.userId.text = user.userId
//        holder.bloodGroup.text = user.bloodGroup
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var userName : TextView = itemView.findViewById(R.id.userName)
        var userDesignation : TextView = itemView.findViewById(R.id.userDesignation)
        var userId : TextView = itemView.findViewById(R.id.userId)
        var bloodGroup : TextView = itemView.findViewById(R.id.bloodGroup)

        fun bindView(user : UserModel){
            userName.text = user.username
            userDesignation.text = user.designation
            userId.text = user.userId
            bloodGroup.text = user.bloodGroup
        }
    }
}