package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.greencycle.databinding.Fragment1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAnnouncement()
        binding.plastic.setOnClickListener { view: View ->
            getAnnouncementsCategorie("plastic")
        }
        binding.organic.setOnClickListener { view: View ->
            getAnnouncementsCategorie("organic")
        }
        binding.glass.setOnClickListener { view: View ->
            getAnnouncementsCategorie("glass")
        }
        binding.paper.setOnClickListener { view: View ->
            getAnnouncementsCategorie("paper")
        }
    }

    private fun   getAnnouncement() {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getannouncements()
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    binding.recycler.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.col))
                    binding.recycler.adapter = MyAdapter(requireActivity(),announcements!!)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
    private fun   getAnnouncementsCategorie(categorie:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getannouncementsCategorie(categorie)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    binding.recycler.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.col))
                    binding.recycler.adapter = MyAdapter(requireActivity(),announcements!!)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}