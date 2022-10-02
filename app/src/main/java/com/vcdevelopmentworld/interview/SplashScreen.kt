package com.vcdevelopmentworld.interview

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import android.os.Bundle
import com.vcdevelopmentworld.interview.R
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.initialization.InitializationStatus
import android.content.Intent
import com.vcdevelopmentworld.interview.AddQuestion
import com.google.android.gms.ads.AdListener
import androidx.core.app.NavUtils
import com.vcdevelopmentworld.interview.MainActivity
import android.widget.TextView
import android.os.Build
import com.vcdevelopmentworld.interview.ResepAdapter.ResepListener
import androidx.recyclerview.widget.RecyclerView
import com.vcdevelopmentworld.interview.ResepAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.ActivityNotFoundException
import android.os.Handler
import hotchemi.android.rate.AppRate
import hotchemi.android.rate.OnClickButtonListener
import com.vcdevelopmentworld.interview.ResepAdapter.ResepHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SplashScreen : AppCompatActivity() {
    var splash: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splash = findViewById(R.id.splash)
        Handler().postDelayed({
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }, splashscreenTiomeout.toLong())
    }

    companion object {
        private const val splashscreenTiomeout = 2000
    }
}