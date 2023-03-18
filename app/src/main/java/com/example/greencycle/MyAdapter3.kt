package com.example.greencycle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.greencycle.R

import com.example.greencycle.announcement
import com.example.greencycle.databinding.ActivityMainBinding
import com.example.greencycle.databinding.AnnouncementLayoutBinding
import com.example.greencycle.databinding.OrderLayoutacceptedBinding
import com.example.greencycle.databinding.OrderLayoutwaitingBinding
import com.example.greencycle.url

class MyAdapter3(val context: Context, var data:List<announcement>): RecyclerView.Adapter<MyAdapter3.MyViewHolder>() {
    lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(OrderLayoutacceptedBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].name
            owner.text = data[position].username
            Glide.with(context).load(url+data[position].image).into(image)
            prix.text = data[position].prix.toString()
            poids.text  = data[position].poids.toString()+ "Kg"
        }



    }


        class MyViewHolder(val binding: OrderLayoutacceptedBinding) : RecyclerView.ViewHolder(binding.root) {

        }

}