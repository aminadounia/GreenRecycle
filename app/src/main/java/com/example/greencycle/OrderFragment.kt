package com.example.greencycle

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greencycle.databinding.FragmentOrderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)
       getAnnouncementswaiting(id)
        binding.waiting.setOnClickListener { view: View ->
            binding.waiting.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.waiting.setTextColor(Color.parseColor("#FFFFFF"))
            binding.accepted.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.accepted.setTextColor(Color.parseColor("#000000"))
            getAnnouncementswaiting(id)

        }
        binding.accepted.setOnClickListener { view: View ->

            binding.accepted.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.accepted.setTextColor(Color.parseColor("#FFFFFF"))
            binding.waiting.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.waiting.setTextColor(Color.parseColor("#000000"))
            getAnnouncementsaccepted(id)

        }



    }
    private fun getAnnouncementswaiting(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getannouncementswaiting(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    if (announcements != null) {
                        binding.recycler2.layoutManager = GridLayoutManager(
                            requireActivity(),
                            resources.getInteger(R.integer.col)
                        )
                        binding.recycler2.adapter = MyAdapter2(requireActivity(), announcements!!)
                    }
                    //else {view?.findNavController()?.navigate(R.id.action_fragment2_to_noreservation)}
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

    private fun getAnnouncementsaccepted(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getannouncementsaccepted(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val announcements = response.body()
                    if (announcements != null) {
                        binding.recycler2.layoutManager = GridLayoutManager(
                            requireActivity(),
                            resources.getInteger(R.integer.col)
                        )
                        binding.recycler2.adapter = MyAdapter3(requireActivity(), announcements!!)
                    }
                    //else {view?.findNavController()?.navigate(R.id.action_fragment2_to_noreservation)}
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}
