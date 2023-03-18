package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.example.greencycle.databinding.FragmentDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

lateinit var binding: FragmentDetailBinding


class detailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val announcement = arguments?.getSerializable("data") as announcement
        if (announcement != null) {
            binding.name.text = announcement.name
            binding.description.text = announcement.description
            binding.prix.text= announcement.prix.toString() + "DZD"
            binding.prix2.text= announcement.prix.toString()
            Glide.with(requireContext()).load(url +announcement.image).into(binding.image2)
            binding.poids.text= announcement.poids.toString() +"Kg"

            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            val id=pref.getInt("idUser",0)

            binding.order2.setOnClickListener{ view: android.view.View ->
                val order= Order(announcement.id, id)
                updateAnnouncement(order)

            }

        }

    }
    private fun updateAnnouncement(order: Order) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.updateannouncement(order)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    Toast.makeText(requireContext(),"Your order was saved", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_detailFragment_to_orderFragment)
                }
                else {

                    Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                }


            }



        }


    }

}