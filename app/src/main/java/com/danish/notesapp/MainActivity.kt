package com.danish.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0F

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?

//        if(navHostFragment != null){
//            val appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
////            findViewById<Toolbar>(R.id.toolbar_home)
////                .setupWithNavController(navHostFragment.navController, appBarConfiguration)
//
//            setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
//
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_home, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}