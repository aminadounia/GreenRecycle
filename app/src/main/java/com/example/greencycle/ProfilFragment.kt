package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greencycle.databinding.FragmentActivityBinding
import com.example.greencycle.databinding.FragmentProfilBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilFragment : Fragment() {

    lateinit var binding: FragmentProfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)
        getUserbyid(id)
        binding.activity.setOnClickListener { view: View ->
            binding.activity.setBackgroundColor(Color.parseColor("#9DD549"))
            binding.activity.setTextColor(Color.parseColor("#FFFFFF"))
            binding.personal.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.personal.setTextColor(Color.parseColor("#000000"))
            view.findNavController().navigate(R.id.action_profilFragment_to_activityFragment)
        }
        binding.exit.setOnClickListener {
            pref.edit {
                putBoolean("connected", false)

            }
            val intent = Intent(requireActivity(), MainActivity0::class.java)
            this.startActivity(intent)
            requireActivity().finish()
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
                        binding.phone3.text=user.num
                        binding.email.text=user.mail
                        binding.crn.text=user.nif.toString()
                        binding.rc.text=user.rc.toString()
                        binding.sin2.text=user.nis.toString()
                    }

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}