package com.vcdevelopmentworld.interview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.vcdevelopmentworld.interview.MainActivity
import com.vcdevelopmentworld.interview.databinding.ActivitySplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {

    companion object {
        private const val splashscreenTiomeout = 4000
    }

    override fun initControl() {
        Handler().postDelayed({
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }, splashscreenTiomeout.toLong())

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator
            .addUpdateListener { animation: ValueAnimator ->
                binding.animationView
                    .setProgress(
                        animation
                            .animatedValue as Float
                    )
            }
        animator.start()
    }

    override fun getViewBinding(): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)
}