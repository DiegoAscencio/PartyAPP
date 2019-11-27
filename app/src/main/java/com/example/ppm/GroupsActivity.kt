package com.example.ppm

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ppm.Fragments.FragmentGroups
import com.google.android.material.bottomnavigation.BottomNavigationView
import iteso.mx.fragments.fragments.FragmentGame
import iteso.mx.fragments.fragments.FragmentMail
import iteso.mx.fragments.fragments.FragmentShare

class GroupsActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        supportActionBar?.hide()

        val navigation = findViewById<BottomNavigationView>(R.id.activity_main_bnv_navigation)
        navigation.setOnNavigationItemSelectedListener(this)
        navigation.menu.findItem(R.id.action_game).isChecked = true

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_fl_main_content,
                FragmentGame()
            )
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_mail-> supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl_main_content,
                    FragmentMail()
                )
                .commit()
            R.id.action_game -> supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl_main_content, FragmentGame())
                .commit()
            R.id.action_groups -> supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl_main_content, FragmentGroups())
                .commit()
            R.id.action_share -> supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fl_main_content, FragmentShare())
                .commit()
        }
        return true
    }
}

