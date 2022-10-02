package com.vcdevelopmentworld.interview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vcdevelopmentworld.interview.R
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.initialization.InitializationStatus
import android.content.Intent
import com.vcdevelopmentworld.interview.AddQuestion
import androidx.core.app.NavUtils
import com.vcdevelopmentworld.interview.MainActivity
import android.widget.TextView
import android.os.Build
import com.vcdevelopmentworld.interview.ResepAdapter.ResepListener
import androidx.recyclerview.widget.RecyclerView
import com.vcdevelopmentworld.interview.ResepAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.ActivityNotFoundException
import android.text.Layout
import hotchemi.android.rate.AppRate
import hotchemi.android.rate.OnClickButtonListener
import com.vcdevelopmentworld.interview.ResepAdapter.ResepHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.gms.ads.*

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val toolbar = supportActionBar
        toolbar!!.setDisplayHomeAsUpEnabled(true)
        bindData()

        val add = findViewById<Button>(R.id.add)
        add.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, AddQuestion::class.java)
            startActivity(i)
            finish()
        })
    }


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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_bahan.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }

        //TextView tv_langkah = (TextView) findViewById(R.id.langkah);
        //tv_langkah.setText(steps[position]);
    }
}