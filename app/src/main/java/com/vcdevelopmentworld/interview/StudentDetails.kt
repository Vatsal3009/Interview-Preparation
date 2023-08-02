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
import hotchemi.android.rate.AppRate
import hotchemi.android.rate.OnClickButtonListener
import com.vcdevelopmentworld.interview.ResepAdapter.ResepHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StudentDetails {
    var studentName: String? = null
    var studentPhoneNumber: String? = null
}