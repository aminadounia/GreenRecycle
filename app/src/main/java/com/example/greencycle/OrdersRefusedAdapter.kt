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
import com.example.greencycle.databinding.ActivityMain2Binding
import com.example.greencycle.databinding.ActivityMainBinding
import com.example.greencycle.databinding.AnnouncementLayoutBinding
import com.example.greencycle.databinding.OrderAcceptedProviderLayoutBinding
import com.example.greencycle.databinding.OrderWaitingProviderLayoutBinding
import com.example.greencycle.url

class OrdersRefusedAdapter(val context: Context, var data:List<announcement>): RecyclerView.Adapter<OrdersRefusedAdapter.MyViewHolder>() {
    lateinit var binding : ActivityMain2Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(OrderWaitingProviderLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].name
            owner.text = data[position].id_user.toString()

            Glide.with(context).load(url+data[position].image).into(image)
            prix.text = data[position].prix.toString()
            poids.text = data[position].poids.toString()


        }




    }


        class MyViewHolder(val binding: OrderWaitingProviderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        }

}