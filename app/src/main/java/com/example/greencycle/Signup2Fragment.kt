package com.example.greencycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.greencycle.databinding.FragmentSignup2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class Signup2Fragment : Fragment() {

    lateinit var binding: FragmentSignup2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignup2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = arguments?.getSerializable("data") as User
        binding.signup.setOnClickListener { view: View ->




            user.nif=binding.nif.text.toString().toInt()
            user.rc=binding.rc.text.toString().toInt()
            user.nis=binding.mic.text.toString().toInt()


            addUser(user)

        }

    }
    private fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.adduser(user)
            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            withContext(Dispatchers.Main) {
                binding.signup.isEnabled  = true

                if (response.isSuccessful) {
                    pref.edit {
                        putBoolean("connected", true)

                    }
                    val intent = Intent(requireActivity(), MainActivity2::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                else {

                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }


            }



        }


    }
}