package com.gabrielportari.revisioncomponents.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.model.UserData

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: UserData){
        itemView.findViewById<TextView>(R.id.text_user_email).text = user.email
        itemView.findViewById<TextView>(R.id.text_user_name).text = user.name
        itemView.findViewById<TextView>(R.id.text_user_phone).text = user.phone
    }
}