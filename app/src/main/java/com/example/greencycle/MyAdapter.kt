package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.greencycle.R

import com.example.greencycle.announcement
import com.example.greencycle.databinding.ActivityMainBinding
import com.example.greencycle.databinding.AnnouncementLayoutBinding
import com.example.greencycle.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAdapter(val context: Context, var data:List<announcement>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(AnnouncementLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
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

        holder.itemView.setOnClickListener { view: View ->
            val data = bundleOf("data" to data[position])
            view.findNavController().navigate(R.id.action_fragment1_to_detailFragment,data)


        }


    }


        class MyViewHolder(val binding: AnnouncementLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        }



}