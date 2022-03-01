package com.alireza.hw13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alireza.quizandroidthree.R
import com.alireza.quizandroidthree.User
import com.alireza.quizandroidthree.UserXItem

class PhotoRecyclerAdapter(listOfUser: List<UserXItem>) :
    RecyclerView.Adapter<PhotoRecyclerAdapter.UserItem>() {

    var listOfUser = listOfUser

    class UserItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.userText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItem {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val item = inflater.inflate(R.layout.user_item, parent, false)
        return UserItem(item)
    }

    override fun onBindViewHolder(holder: UserItem, position: Int) {
        holder.textView.text = listOfUser[position].firstName
    }

    override fun getItemCount(): Int {
        return listOfUser.size
    }


}