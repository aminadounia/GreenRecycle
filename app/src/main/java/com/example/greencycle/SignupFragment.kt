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
import com.example.greencycle.databinding.FragmentSignupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items1 = listOf("Provider Entreprise", "Provider Volunteer", "Recycler")
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items1)

        binding.role.setAdapter(adapter)

        binding.next.setOnClickListener { view: View ->

            val mail = binding.EmailAddress.text.toString()
            val mdp = binding.Password.text.toString()
            val num = binding.number.text.toString()
            val role = binding.role.text.toString()
            val username = binding.username.text.toString()

            val user = User(-1, mail, mdp, username, num, role, null, null, null)
            val data = bundleOf("data" to user)
            if (role=="Provider Entreprise"){
                view.findNavController().navigate(R.id.action_signupFragment_to_signup2Fragment, data)
            }
            else{
                addUser(user)
            }

        }

    }
    private fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.adduser(user)
            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    pref.edit {
                        putBoolean("connected", true)

                    }
                    if(user.role=="Recycler")
                    {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()}
                    if(user.role=="Provider Volunteer")
                    {
                        val intent = Intent(requireActivity(), MainActivity2::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }
                else {

                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }


            }



        }


    }
}