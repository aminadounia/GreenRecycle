package com.example.greencycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.greencycle.databinding.ActivityMain0Binding

class MainActivity0 : AppCompatActivity() {
    lateinit var binding: ActivityMain0Binding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain0Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment = supportFragmentManager. findFragmentById(R.id.fragmentContainerView0) as NavHostFragment
        navController = navHostFragment.navController
      //  NavigationUI.setupWithNavController(binding.navBottom,navController)

    }
}