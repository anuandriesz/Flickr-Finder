package com.assignment.flickerfinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.assignment.flickerfinder.R
import com.assignment.flickerfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initialization()
    }

    /* Add navigation host to MainActivity
     (The MainActivity is associated with a navigation graph and contains a NavHostFragment that is
     responsible for swapping destinations as needed.)*/
    private fun initialization() {
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.searchFragment,
                R.id.imageDetailViewFragment
            )
            .build()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }
}