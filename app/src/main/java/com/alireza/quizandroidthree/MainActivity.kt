package com.alireza.quizandroidthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    lateinit var navigition: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navigition = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navigition.graph)

        NavigationUI.setupActionBarWithNavController(this,navigition,appBarConfiguration)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}