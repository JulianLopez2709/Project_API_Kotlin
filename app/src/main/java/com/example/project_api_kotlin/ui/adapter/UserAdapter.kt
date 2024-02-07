package com.example.project_api_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_api_kotlin.domain.User.Data
import com.example.project_api_kotlin.databinding.ItemUsersBinding

class UserAdapter(var listUser: List<Data>,val click: () -> Unit) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUsersBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listUser.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = listUser[position]
        holder.bind(item,click)
    }

    fun updateList(newList:List<Data>){
        listUser = newList
        notifyDataSetChanged()
    }

}

class UserViewHolder(private val binding: ItemUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Data, click: () -> Unit) {
        Glide.with(binding.root.context).load(item.avatar).into(binding.ivAvatarUser)

        binding.tvEmail.text = item.email

        binding.tvFullname.text = "${item.first_name} ${item.last_name}"

        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, "${item.id} ${item.first_name} ", Toast.LENGTH_SHORT).show()
        }
    }

}
