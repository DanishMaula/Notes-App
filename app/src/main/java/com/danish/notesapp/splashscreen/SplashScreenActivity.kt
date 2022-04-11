package com.danish.notesapp.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.danish.notesapp.MainActivity
import com.danish.notesapp.presentation.home.HomeFragment


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//

        Handler().postDelayed(Runnable
        /*
            * Showing splash screen with a timer. This will be useful when you
            * want to show case your app logo / company
            */
        {
            val mainIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
            // close this activity
        }, 2000
        )
    }
}