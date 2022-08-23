package com.arema.suryeyapp.dashboard

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.arema.suryeyapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

class dashboardFragment : Fragment(R.layout.fragment_dashboard) {
    var completedsurveys = 0
    var pendingsurveys = 0
    private lateinit var sp : SharedPreferences
    var progresdialog : ProgressDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = FirebaseDatabase.getInstance()
        val refrence = db.reference.child("Users")

        sp = requireActivity().getSharedPreferences("userdetails" , Context.MODE_PRIVATE)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val week = c.get(Calendar.WEEK_OF_MONTH)

        if(sp.getString("date" , "null") != "$day/$month/$year" || sp.getString("date" , "null").isNullOrEmpty()
            || sp.getString("date" , "null") == "null")
        {
            val editor = sp.edit()
            editor.putInt("completed" , 0)
            editor.apply()
            val editor1 = sp.edit()
            editor1.putString("date", "$day/$month/$year")
            editor1.apply()

        }

        if(sp.getString("date1" , "null") != "$week/$month/$year" || sp.getString("date1" , "null").isNullOrEmpty()
            || sp.getString("date1" , "null") == "null")
        {
            val editor = sp.edit()
            editor.putInt("completedw" , 0)
            editor.apply()
            val editor1 = sp.edit()
            editor1.putString("date1", "$week/$month/$year")
            editor1.apply()

        }
        progresdialog = ProgressDialog(context)
        progresdialog!!.setTitle("Loading Data!!")
        progresdialog!!.setCancelable(false)
        progresdialog!!.show()


        daily_surveys_tv_ds.text = sp.getInt("completed" , 0).toString()
        weekly_surveys_tv_ds.text = sp.getInt("completedw" , 0).toString()

        notification_btn.setOnClickListener {
            val intent = Intent(context , Notifications::class.java)
            startActivity(intent)

        }

        profilr_img.setOnClickListener {
            val intent = Intent(context , ProfileActivity::class.java)
            startActivity(intent)
        }

       refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("completedsurveys").get().addOnSuccessListener {

                completedsurveys = it.childrenCount.toString().toInt()
                completed_surveys_tv_ds.text = completedsurveys.toString()
            }

        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("pendingsurveys").get().addOnSuccessListener {

                pendingsurveys = it.childrenCount.toString().toInt()
                pending_surveys_tv_ds.text = pendingsurveys.toString()
                progresdialog?.dismiss()
            }
    }
}