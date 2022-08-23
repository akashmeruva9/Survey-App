package com.arema.suryeyapp.form

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arema.suryeyapp.R
import com.arema.suryeyapp.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_carinfo.*

class carinfo : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carinfo)

        val key = intent.getStringExtra("key").toString()
        val dialog = ProgressDialog(this)
        dialog.setTitle("Loading...")
        dialog.setCancelable(false)
        dialog.show()

        db = FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
            .child("completedsurveys")


        db.child(key).child("surveydata").child("location").get().addOnSuccessListener {

            val data = it.value
            Location_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("model").get().addOnSuccessListener {

            val data = it.value
            model_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("odometer").get().addOnSuccessListener {

            val data = it.value
            odometer_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("enginenumber").get().addOnSuccessListener {

            val data = it.value
            engine_no_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("color").get().addOnSuccessListener {

            val data = it.value
            color_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("chasis").get().addOnSuccessListener {

            val data = it.value
            chasis_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("condidtionofbody").get().addOnSuccessListener {

            val data = it.value
            covb_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("conditionofengine").get().addOnSuccessListener {

            val data = it.value
            cove_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("conditionoftyre").get().addOnSuccessListener {

            val data = it.value
            cot_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("remarks").get().addOnSuccessListener {

            val data = it.value
            remarks_tv.text = data.toString()
            dialog.dismiss()
        }

        carinfo_back_btn.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}