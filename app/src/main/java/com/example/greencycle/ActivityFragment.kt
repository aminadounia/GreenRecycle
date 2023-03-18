package com.example.greencycle

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.greencycle.databinding.Fragment1Binding
import com.example.greencycle.databinding.FragmentActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ActivityFragment : Fragment() {
    lateinit var binding: FragmentActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)
        getUserbyid(id)
        binding.personal.setOnClickListener { view: View ->
            binding.personal.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.personal.setTextColor(Color.parseColor("#FFFFFF"))
            binding.activity.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.activity.setTextColor(Color.parseColor("#000000"))
            view.findNavController().navigate(R.id.action_activityFragment_to_profilFragment)
        }

    }
    private fun  getUserbyid(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getuserbyid(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val user = response.body()
                    if(user!=null) {
                        binding.name2.text = user.username
                        binding.role.text=user.role
                    }

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}