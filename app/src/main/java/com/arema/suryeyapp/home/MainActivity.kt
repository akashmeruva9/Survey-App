package com.arema.suryeyapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arema.suryeyapp.R
import com.arema.suryeyapp.dashboard.dashboardFragment
import com.arema.suryeyapp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replacefragment(dashboardFragment())

        binding.bottomNavBar.setOnItemSelectedListener{ item ->
            when (item.itemId)
            {

                R.id.dashboardnavigate ->
                {
                    replacefragment(dashboardFragment())
                }

                R.id.pendingnavigate -> {
                    replacefragment(PendingFragment())
                }

                R.id.completednavigate -> {
                    replacefragment(CompletedFragment())
                }

                R.id.whatsaapnavigate -> {

                    replacefragment(WhatsaapFragment())
                }

            }

            return@setOnItemSelectedListener true
        }
    }

    fun replacefragment(fragment : Fragment)
    {
        val fragmentmanager = supportFragmentManager
        val fragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}