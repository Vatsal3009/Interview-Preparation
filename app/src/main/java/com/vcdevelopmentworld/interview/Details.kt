package com.vcdevelopmentworld.interview

import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.app.NavUtils
import androidx.core.os.postDelayed
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd
import com.vcdevelopmentworld.interview.databinding.ActivityDetailsBinding

class Details : BaseActivity<ActivityDetailsBinding>() {

    private lateinit var mInterstitialAd: PublisherInterstitialAd
    private var mCountDownTimer: CountDownTimer? = null
    private var mGameIsInProgress: Boolean = false
    private var mAdIsLoading: Boolean = false
    private var mTimerMilliseconds: Long = 0

    override fun initControl() {
        bindData()
        loadIndustialAds()
        this.bannerAdsLoad(this, binding.bottomAdView)
        Handler().postDelayed(2000){
            showInterstitial()
        }
    }

    override fun getViewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindData() {
        val position: Int
        val titleresep: String?
        val bahan = resources.getStringArray(R.array.detail_resep)
        //String steps [] = getResources().getStringArray(R.array.step_resep);
        val intent = intent
        titleresep = intent.getStringExtra(Intent.EXTRA_TEXT)
        position = intent.getIntExtra(MainActivity.Companion.POSITION, 0)
        val tv_titleresep = findViewById<View>(R.id.titleresep) as TextView
        tv_titleresep.text = titleresep
        val tv_bahan = findViewById<View>(R.id.bahan) as TextView
        tv_bahan.text = bahan[position]

        //TextView tv_langkah = (TextView) findViewById(R.id.langkah);
        //tv_langkah.setText(steps[position]);
    }

    private fun loadIndustialAds() {
        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = PublisherInterstitialAd(this)
        // Replace with your own ad unit id.
        mInterstitialAd.adUnitId = getString(R.string.interstitial_id)

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                startGame()
            }

            override fun onAdLoaded() {
                mAdIsLoading = false
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mAdIsLoading = false
            }
        }

        // Create the "retry" button, which tries to show an interstitial between game plays.
        startGame()

    }

    private fun startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mAdIsLoading && !mInterstitialAd.isLoaded) {
            mAdIsLoading = true
            val adRequest = PublisherAdRequest.Builder().build()
            mInterstitialAd.loadAd(adRequest)
        }
        resumeGame(3000)
    }

    private fun resumeGame(milliseconds: Long) {
        // Create a new timer for the correct length and start it.
        mGameIsInProgress = true
        mTimerMilliseconds = milliseconds
        createTimer(milliseconds)
        mCountDownTimer?.start()
    }

    private fun createTimer(milliseconds: Long) {
        // Create the game timer, which counts down to the end of the level
        // and shows the "retry" button.
        mCountDownTimer?.cancel()

        mCountDownTimer = object : CountDownTimer(milliseconds, 50) {
            override fun onTick(millisUntilFinished: Long) {
                mTimerMilliseconds = millisUntilFinished
            }

            override fun onFinish() {
                mGameIsInProgress = false
            }
        }
    }

    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            startGame()
        }
    }

    public override fun onPause() {
        binding.bottomAdView.pause()
        mCountDownTimer?.cancel()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        binding.bottomAdView.resume()
        if (mGameIsInProgress) {
            resumeGame(mTimerMilliseconds)
        }
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        super.onDestroy()
        binding.bottomAdView.destroy()
    }


}