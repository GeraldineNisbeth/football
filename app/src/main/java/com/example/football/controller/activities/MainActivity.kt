package com.example.football.controller.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.football.R
import com.example.football.controller.fragments.TeamFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item->navigateTo(item)
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.isChecked= true
        return supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, getFragmentFor(item))//del activity_main
            .commit()>0
    }

    private fun getFragmentFor(item : MenuItem): Fragment{
        return when(item.itemId){
            R.id.menu_home->TeamFragment()
            //R.id.menu_favourite->SaveFragment
            else-> TeamFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener(onNavigationSelectedListener)
        navigateTo(navView.menu.findItem(R.id.menu_home))
    }
}
