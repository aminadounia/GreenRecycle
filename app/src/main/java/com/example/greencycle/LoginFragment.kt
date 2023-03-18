package com.example.greencycle
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.findNavController
import com.example.greencycle.databinding.FragmentLoginBinding
import com.example.greencycle.databinding.FragmentSignupBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//pour la fenetre du login
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var con = pref.getBoolean("connected", false)
        val id=pref.getInt("idUser",0)

        if (con) {
            getUserbyid(id)

        }

        binding.login.setOnClickListener {
            //binding.progressBar.visibility= View.VISIBLE
            var mail: String = binding.EmailAddress.text.toString()
            var mdp: String = binding.Password.text.toString()
            login_methode(mail, mdp)
        }
        binding.singup.setOnClickListener {

            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }


//Ceci est la methode de verification des champs pour se connecter
    private  fun login_methode(number: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getuser(number,password)
            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            //  var con = pref.getBoolean("connected", false)
            //quand il termine il retourne au thread main
            withContext(Dispatchers.Main) {
                //       binding.progressBar.visibility= View.INVISIBLE

                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        pref.edit {
                            putInt("idUser",user.id)
                            putBoolean("connected", true)

                        }

                        if(user.role=="Recycler"){
                            val intent = Intent(requireActivity(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                        else{
                            val intent = Intent(requireActivity(), MainActivity2::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    } else {

                        Toast.makeText(
                            requireActivity(),
                            "numero ou mot de passe errone",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                else {

                    Toast.makeText(requireActivity(),"une erreur 200",Toast.LENGTH_SHORT).show()}
            }

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
                     if(user.role=="Recycler"){
                         val intent = Intent(requireActivity(), MainActivity::class.java)
                         startActivity(intent)
                         requireActivity().finish()
                     }
                        else{
                         val intent = Intent(requireActivity(), MainActivity2::class.java)
                         startActivity(intent)
                         requireActivity().finish()
                        }
                    }

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}