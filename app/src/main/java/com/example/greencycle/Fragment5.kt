package com.example.greencycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greencycle.databinding.Fragment4Binding
import com.example.greencycle.databinding.Fragment5Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment5 : Fragment() {


    lateinit var binding: Fragment5Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = Fragment5Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        val items1 = listOf("paper", "organic", "glass","plastic")
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var id=pref.getInt("idUser",0)
        binding.catecory.setAdapter(adapter)
        binding.scan2.setOnClickListener { view: View ->

            val product = binding.product.text.toString()
            val price = binding.price.text.toString().toInt()
            val quantite = binding.quantity.text.toString().toInt()
            val catecory = binding.catecory.text.toString()
            val descr = binding.descr.text.toString()

            val anouncement = announcementSimple(-1, product,id ,price , quantite, descr,"/images/water.png","waiting",catecory,null)
            val data = bundleOf("data" to anouncement)
            addAnnouncement(anouncement)
            view.findNavController().navigate(R.id.action_fragment5_to_scannerFragment)

        }

    }
    private fun addAnnouncement(announcement:announcementSimple) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.addAnnouncement(announcement)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {

                    Toast.makeText(requireActivity(),"Announcement saved", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}