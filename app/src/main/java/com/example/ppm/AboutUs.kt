package com.example.ppm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class AboutUs: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abous_us)
        supportActionBar?.hide()
    }

    fun returnMain(view: View) {
        startActivity<MainActivity>()
    }

}