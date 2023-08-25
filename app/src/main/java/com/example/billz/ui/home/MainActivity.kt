package com.example.billz.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.billz.PaidBills
import com.example.billz.R
import com.example.billz.Settings
import com.example.billz.Summary
import com.example.billz.UpcomingBills
import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = Summary()
        val secondFragment = UpcomingBills()
        val thirdFragment = PaidBills()
        val fourthFragment = Settings()

        setCurrentFragment(firstFragment)

        val navView: BottomNavigationView = binding.bottomNavigation
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_summary -> setCurrentFragment(firstFragment)
                R.id.nav_upcoming -> setCurrentFragment(secondFragment)
                R.id.nav_paid -> setCurrentFragment(thirdFragment)
                R.id.nav_settings -> setCurrentFragment(thirdFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }
}