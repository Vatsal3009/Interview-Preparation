package com.vcdevelopmentworld.interview

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import android.os.Bundle
import com.vcdevelopmentworld.interview.R
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.initialization.InitializationStatus
import android.content.Intent
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
import android.net.Uri
import android.util.Log
import hotchemi.android.rate.AppRate
import hotchemi.android.rate.OnClickButtonListener
import com.vcdevelopmentworld.interview.ResepAdapter.ResepHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vcdevelopmentworld.interview.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(), ResepListener {

    companion object {
        const val POSITION = "position"
    }

    private lateinit var adapter: ResepAdapter


    override fun initControl() {
        val title = resources.getStringArray(R.array.title_resep)
        adapter = ResepAdapter(title, this)
        binding.rc.setAdapter(adapter)

        if (isInternetAvailable(this)) {
            // Internet is available, proceed with your network request or action
        } else {
            // Internet is not available, show the custom dialog
            showNoInternetDialog(this)
        }


        binding.rateMe.setOnClickListener {
            val a = Intent(Intent.ACTION_SEND)
            val appPackageNAm = applicationContext.packageName
            val strAppLink = ""
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageNAm")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$appPackageNAm")
                    )
                )
            }
        }
        binding.share.setOnClickListener {
            val a = Intent(Intent.ACTION_SEND)
            val appPackageNAme = applicationContext.packageName
            var strAppLink = ""
            strAppLink = try {
                "http://play.google.com/store/apps/details?id=$appPackageNAme"
            } catch (anfe: ActivityNotFoundException) {
                "https:/play.google.com/store/apps/details?id=$appPackageNAme"
            }
            a.type = "text/link"
            val sharebody = """
                Toughest Interview Questions and Answers app covers all tough questions which is difficult to answer. 
                
                This app helps your to find out what an employer wants most in his or her ideal candidate, then show how you meet those qualifications. 
                
                Please Review and Rate Us... 
                
                Download this Exclusive application and Share it...
                
                $strAppLink
                """.trimIndent()
            val sharesub = "APP NAME "
            a.putExtra(Intent.EXTRA_SUBJECT, sharesub)
            a.putExtra(Intent.EXTRA_TEXT, sharebody)
            startActivity(Intent.createChooser(a, "Share Using"))
        }
        binding.buttonShowCustomDialog.setOnClickListener { // custom dialog
            val dialog = Dialog(this@MainActivity)
            dialog.setContentView(R.layout.custom)

            // set the custom dialog components - text, image and button
            val title_law = dialog.findViewById<View>(R.id.title_law) as TextView
            val text = dialog.findViewById<View>(R.id.text) as TextView
            text.text =
                "-  Multiple copies of resume, credentials and photos. \n- Dress well. \n-Punctuality. \n- Research the company and position. \n- How can you benefit them? \n- Recall your past achievements. \n- Any references? \n- Presence of mind. \n- Knowledge of what you have written in your resume. \n- Negotiation skills."
            val dialogButton = dialog.findViewById<View>(R.id.dialogButtonOK) as Button
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener { dialog.dismiss() }
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
        AppRate.with(this).setInstallDays(0)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .setOnClickButtonListener { which ->
                Log.d(
                    MainActivity::class.java.name,
                    Integer.toString(which)
                )
            }
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)
        AppRate.with(this).shouldShowRateDialog()

    }


    override fun onClick(position: Int) {
        if (isInternetAvailable(this)) {
            val i = Intent(this, Details::class.java)
            i.putExtra(Intent.EXTRA_TEXT, adapter!!.getJudul(position))
            i.putExtra(POSITION, position)
            startActivity(i)
        } else {
            // Internet is not available, show the custom dialog
            showNoInternetDialog(this)
        }

    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}