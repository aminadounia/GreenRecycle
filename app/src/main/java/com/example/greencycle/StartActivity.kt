package com.example.greencycle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.greencycle.databinding.ActivityStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//cette activity est concu pour la premiere fenetre de bienvenu
class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val pref = getSharedPreferences("user_db", Context.MODE_PRIVATE)
        var  con = pref.getBoolean("connected", false)
        val id=pref.getInt("idUser",0)

        if (con) {
            getUserbyid(id)

        }
        //si l'application a deja été ouverte dans le telephone -> on accede au menu directement
        else {
            binding.button.setOnClickListener {
                val intent = Intent(this, MainActivity0::class.java)
                startActivity(intent)
                finish()
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
                        //si le user est un recycler -> go to graph1
                        if(user.role=="Recycler"){
                            val intent = Intent(this@StartActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        //si le user est un provider -> go to graph2
                        else{
                            val intent = Intent(this@StartActivity, MainActivity2::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                }
                else {
                    Toast.makeText(this@StartActivity,"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}