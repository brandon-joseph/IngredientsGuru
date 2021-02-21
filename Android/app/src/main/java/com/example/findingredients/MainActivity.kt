package com.example.findingredients

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val fragment1: Fragment = searchFragment()
    val fragment2: Fragment = savedFragment()
    val fragmentManager = supportFragmentManager;
    var active = fragment1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  bottomNavBar: BottomNavigationView = findViewById(R.id.bottomnav)
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(
            fragment2
        ).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();

        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_item -> {
                    Log.d("myTag", "On home")
//
//                    fragmentManager.beginTransaction()
//                        .remove(savedFragment())
//                        .add(R.id.fragment_container, searchFragment())
//                        .commit()
                    fragmentManager.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;


                }
                R.id.profile_item -> {
                    Log.d("myTag", "On profile")
//                    fragmentManager.beginTransaction()
//                        .remove(searchFragment())
//                        .replace(R.id.fragment_container, savedFragment())
//                        .commit()
                    fragmentManager.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;


                }
            }
            true
        }
    }

}