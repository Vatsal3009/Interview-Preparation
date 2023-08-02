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
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ResepAdapter(private val mJudul: Array<String?>, listener: MainActivity) :
    RecyclerView.Adapter<ResepHolder>() {
    //private String mLangkah[];
    private val mListener: ResepListener

    internal interface ResepListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResepHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.front_design, parent, false)
        return ResepHolder(view)
    }

    override fun onBindViewHolder(holder: ResepHolder, position: Int) {
        val judul = mJudul[position]
        //String langkah = mLangkah[position];
        holder.setResep(judul)
    }

    override fun getItemCount(): Int {
        return mJudul.size
    }

    fun getJudul(position: Int): String? {
        return mJudul[position]
    }

    inner class ResepHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener {
        private val textView1: TextView
        fun setResep(judul: String?) {
            textView1.text = judul
            //textView2.setText(ket);
        }

        override fun onClick(v: View) {
            mListener.onClick(adapterPosition)
        }

        init {
            textView1 = itemView.findViewById<View>(R.id.title) as TextView
            itemView.setOnClickListener(this)
        }
    }

    init {
        //mLangkah = langkah;
        mListener = listener
    }
}