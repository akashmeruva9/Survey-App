package com.arema.suryeyapp.form

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arema.suryeyapp.R
import com.arema.suryeyapp.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.dialog_alert.*
import java.util.*


class Details : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener{


    var number:String? = null
    var day = 0
    var month= 0
    var year= 0
    var hour= 0
    var minute = 0
    var myday = 0
    var myMonth  = 0
    var myYear  = 0
    var myHour = 0
    var myMinute = 0
    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

         key = intent.getStringExtra("key").toString()
        val type = intent.getStringExtra("type")

        getdata(key , type)

        details_back_btn.setOnClickListener {
            super.onBackPressed()
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        cv3.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL)
            val temp = "tel:${number}"
            intent.data = Uri.parse(temp)
            startActivity(intent)

        }

        cv1.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, this@Details  , year, month, day)
            datePickerDialog.show()
        }

        start_survey_btn.setOnClickListener {

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_alert)
            dialog.show()

            dialog.drp_yes.setOnClickListener {

                if(type == "car") {

                    FirebaseDatabase.getInstance().reference.child("Users")
                        .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                        .child("pendingsurveys").child(key)
                        .child("status").get().addOnSuccessListener{

                            if(it.value.toString() == "imguploaded")
                            {
                                val intent = Intent(this, carfillform::class.java)
                                intent.putExtra("key", key)
                                intent.putExtra("type", type)
                                startActivity(intent)
                                this.finish()
                            }else
                            {
                                val intent = Intent(this, carimageupload::class.java)
                                intent.putExtra("key", key)
                                intent.putExtra("type", type)
                                startActivity(intent)
                                this.finish()
                            }
                        }

                }else if(type == "build")
                {

                    FirebaseDatabase.getInstance().reference.child("Users")
                        .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                        .child("pendingsurveys").child(key)
                        .child("status").get().addOnSuccessListener {

                            if(it.value.toString() == "imguploaded")
                            {
                                val intent = Intent(this, buildfillform::class.java)
                                intent.putExtra("key", key)
                                intent.putExtra("type", type)
                                startActivity(intent)

                                this.finish()
                            }else
                            {
                                val intent = Intent(this, buildimageupload::class.java)
                                intent.putExtra("key", key)
                                intent.putExtra("type", type)
                                startActivity(intent)
                                this.finish()
                            }
                        }
                }

                dialog.dismiss()
            }

            dialog.drp_no.setOnClickListener {
                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    private fun getdata(key: String?, type: String?) {

        val progresdialog = ProgressDialog(this)
        progresdialog.setTitle("Loading Data!!")
        progresdialog.setCancelable(false)
        progresdialog.show()

        FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("pendingsurveys").child(key!!).child("name").get().addOnSuccessListener {

                detail_name_tv.text = "Name            :  ${it.value.toString()}"
            }

        FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("pendingsurveys").child(key).child("address").get().addOnSuccessListener {

                detail_adress_tv.text = "Address        :  \n\n${it.value.toString()}"
            }
        FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("pendingsurveys").child(key).child("phone").get().addOnSuccessListener {

                detail_phone_tv.text = "Phone No.     :  ${it.value.toString()}"
                 number = it.value.toString()
                progresdialog.dismiss()
            }

        if(type == "car") {
            FirebaseDatabase.getInstance().reference.child("Users")
                .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                .child("pendingsurveys").child(key).child("vehiclenumber").get()
                .addOnSuccessListener {

                    detail_vehicle_tv.text = "Vehicle No.    : ${it.value.toString()}"

                }
        }else
        {
            detail_vehicle_tv.visibility = View.GONE
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myYear = year
        myday = dayOfMonth
        myMonth = month
        val c = Calendar.getInstance()
        hour = c[Calendar.HOUR]
        minute = c[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(this, this, hour, minute, false)
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        var ampm: String? = null
        if(hourOfDay > 12)
        {
            myHour = hourOfDay - 12
            ampm = "PM"
        }else if(hourOfDay == 12)
        {
            myHour = hourOfDay
            ampm = "PM"
        }else
        {
            myHour = hourOfDay
            ampm = "AM"
        }

        myMinute = minute
        val progresdialog = ProgressDialog(this)
        progresdialog.setTitle("Uploading Data !!")
        progresdialog.setCancelable(false)
        progresdialog.show()

        FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
            .child("pendingsurveys").child(key).child("date").setValue("$myday-${myMonth+1}-$myYear")

        FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
            .child("pendingsurveys").child(key).child("time").setValue("$myHour:$myMinute $ampm").addOnSuccessListener {
                progresdialog.dismiss()
                Toast.makeText(this , "Date and Time Uploaded Successfully !" , Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this , "Failed to upload Data !" , Toast.LENGTH_LONG).show()

            }

    }
}