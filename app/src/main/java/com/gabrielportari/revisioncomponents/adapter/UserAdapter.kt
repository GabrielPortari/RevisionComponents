package com.gabrielportari.revisioncomponents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.model.UserData
import com.gabrielportari.revisioncomponents.viewHolder.UserViewHolder

class UserAdapter(private val users: List<UserData>) : RecyclerView.Adapter<UserViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }
}