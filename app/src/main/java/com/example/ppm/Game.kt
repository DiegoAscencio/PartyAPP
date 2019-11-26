package com.example.ppm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Game  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()

        }
}