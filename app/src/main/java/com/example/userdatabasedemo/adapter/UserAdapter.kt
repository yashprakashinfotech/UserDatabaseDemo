package com.example.userdatabasedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabasedemo.R
import com.example.userdatabasedemo.database.User

class UserAdapter(val context: Context, var userList : ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.user_view,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = userList[position]
        holder.useName.text = user.username
        holder.userDesignation.text = user.designation
        holder.userId.text = user.userId
        holder.bloodGroup.text = user.bloodGroup
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var useName : TextView = itemView.findViewById(R.id.userName)
        var userDesignation : TextView = itemView.findViewById(R.id.userDesignation)
        var userId : TextView = itemView.findViewById(R.id.userId)
        var bloodGroup : TextView = itemView.findViewById(R.id.bloodGroup)
    }
}