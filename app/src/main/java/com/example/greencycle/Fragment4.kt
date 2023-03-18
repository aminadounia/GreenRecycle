package com.example.greencycle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greencycle.databinding.Fragment4Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Fragment4 : Fragment() {


    lateinit var binding: Fragment4Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = Fragment4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add2.setOnClickListener{
            view.findNavController().navigate(R.id.action_fragment4_to_fragment5)
        }
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var id=pref.getInt("idUser",0)
        getannouncementsUser(id)



    }
    private fun   getannouncementsUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getannouncementsUser(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    binding.recycler1.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.col))
                    binding.recycler1.adapter = AnnouncementProviderAdapter(requireActivity(),announcements!!)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}