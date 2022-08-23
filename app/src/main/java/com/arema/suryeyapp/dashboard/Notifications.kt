package com.arema.suryeyapp.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arema.suryeyapp.R
import kotlinx.android.synthetic.main.activity_notifications.*

class Notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        notification_back_btn.setOnClickListener {
            super.onBackPressed()
            finish()
        }

        notification_back_btn_down.setOnClickListener {
            super.onBackPressed()
            finish()
        }
    }
}