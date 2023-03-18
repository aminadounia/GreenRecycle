package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.greencycle.R

import com.example.greencycle.announcement
import com.example.greencycle.databinding.ActivityMain2Binding
import com.example.greencycle.databinding.ActivityMainBinding
import com.example.greencycle.databinding.AnnouncementLayoutBinding
import com.example.greencycle.databinding.OrderAcceptedProviderLayoutBinding
import com.example.greencycle.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrdersAcceptedAdapter(val context: Context, var data:List<announcement>): RecyclerView.Adapter<OrdersAcceptedAdapter.MyViewHolder>() {
    lateinit var binding : ActivityMain2Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(OrderAcceptedProviderLayoutBinding.inflate(LayoutInflater.from(context), parent, false))

    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].name
            owner.text = data[position].username
            var latitude = 38.4
            var longtitude = 39.5
            gps.setOnClickListener {
                val uri = "http://maps.google.com/maps?saddr=&daddr=${latitude},${longtitude}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
                context.startActivity( intent, null)
            }
            Glide.with(context).load(url + data[position].image).into(image)
            prix.text = data[position].prix.toString()
            poids.text = data[position].poids.toString() + "Kg"
            var tel = "0557837491"
            call.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$tel")
                context.startActivity(intent)
            }
        }

    }

        class MyViewHolder(val binding: OrderAcceptedProviderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        }


}