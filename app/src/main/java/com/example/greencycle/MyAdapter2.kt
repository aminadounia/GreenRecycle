package com.example.greencycle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.greencycle.R

import com.example.greencycle.announcement
import com.example.greencycle.databinding.ActivityMainBinding
import com.example.greencycle.databinding.AnnouncementLayoutBinding
import com.example.greencycle.databinding.OrderLayoutwaitingBinding
import com.example.greencycle.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAdapter2(val context: Context, var data:List<announcement>): RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {
    lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(OrderLayoutwaitingBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            name.text = data[position].name
            owner.text = data[position].username
            Glide.with(context).load(url+data[position].image).into(image)
            prix.text = data[position].prix.toString()
            poids.text  = data[position].poids.toString()+ "Kg"

            cancel.setOnClickListener{ view: View ->
               val id_order=data[position].id
                updateAnnouncementcancel(id_order)

              //  view.findNavController().navigate(R.id.action_orderFragment_to_fragment1)


            }

        }





    }


        class MyViewHolder(val binding: OrderLayoutwaitingBinding) : RecyclerView.ViewHolder(binding.root) {

        }
    private fun updateAnnouncementcancel(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.updateannouncementcancel(id)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                     Toast.makeText(context,"Your order was canceled", Toast.LENGTH_SHORT).show()

                }
                else {

                    Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                }


            }



        }


    }

}