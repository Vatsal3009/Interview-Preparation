package com.vcdevelopmentworld.interview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.LoadAdError


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    abstract fun initControl()
    protected abstract fun getViewBinding(): VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initControl()

        if (isInternetAvailable(this)) {
            // Internet is available, proceed with your network request or action
        } else {
            // Internet is not available, show the custom dialog
            showNoInternetDialog(this)
        }


    }



}