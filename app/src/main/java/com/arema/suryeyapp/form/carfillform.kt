package com.arema.suryeyapp.form

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arema.suryeyapp.cardetails
import com.arema.suryeyapp.GetAddressIntentService
import com.arema.suryeyapp.R
import com.arema.suryeyapp.home.MainActivity
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_carfillform.*
import java.util.concurrent.TimeUnit

class carfillform : AppCompatActivity() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 2
    private var addressResultReceiver: carfillform.LocationAddressResultReceiver? = null
    private var currentLocation: Location? = null
    private var locationCallback: LocationCallback? = null
    private lateinit var key : String
    private lateinit var sp : SharedPreferences
    var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carfillform)

        sp = this.getSharedPreferences("userdetails", MODE_PRIVATE)
        key = intent.getStringExtra("key").toString()
        val db = FirebaseDatabase.getInstance()
        val refrence = db.reference.child("Users")

        start_timer()
        addressResultReceiver = LocationAddressResultReceiver(Handler(), this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                currentLocation = locationResult.locations[0]
                getAddress()
            }
        }
        startLocationUpdates()

        continue_survey_btn.setOnClickListener {
            if (checkdata() == true) {

                val progresdialog = ProgressDialog(this)
                progresdialog.setTitle("Updating Data!!")
                progresdialog.setCancelable(false)
                progresdialog.show()

                val model = model_et.text.toString()
                val odometer = odometer_et.text.toString()
                val enginenumber = engine_no_et.text.toString()
                val color = color_et.text.toString()
                val chasis = chasis_et.text.toString()
                val condidtionofbody = covb.text.toString()
                val conditionofengine = cove.text.toString()
                val conditionoftyre = cot.text.toString()
                val remarks = remarks_et.text.toString()
                val location = Location_tv.text.toString()
                val status = "completed"

                val data = cardetails(model, odometer, enginenumber, color, chasis, condidtionofbody,
                    conditionofengine, conditionoftyre, remarks, location)

                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key)
                    .child("status").setValue("completed")

                refrence.child(
                    (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key).child("surveydata")
                    .setValue(data)
                    .addOnSuccessListener {

                        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).get().addOnSuccessListener {

                                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                                    .child("completedsurveys").child(key).setValue(it.value)
                                    .addOnSuccessListener {

                                        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                                            .child("pendingsurveys").child(key).removeValue()
                                            .addOnSuccessListener {

                                                val a = sp.getInt("completed", 999)

                                                if (sp.getInt("completed", 999) != 999 && sp.getInt("completed", 999) != null) {
                                                    val editor = sp.edit()
                                                    editor.putInt("completed", a + 1)
                                                    editor.commit() }

                                                val a1 = sp.getInt("completedw", 999)

                                                if (sp.getInt("completedw", 999) != 999 && sp.getInt("completedw", 999) != null) {
                                                    val editor = sp.edit()
                                                    editor.putInt("completedw", a1 + 1)
                                                    editor.commit() }

                                                progresdialog.dismiss()
                                                val intent = Intent(this, MainActivity::class.java)
                                                startActivity(intent)
                                                this.finish()



                                            }
                                    }
                            }
                    }
            }
        }

        carfillform_back_btn.setOnClickListener {

                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()

        }
    }




    private fun checkdata(): Boolean  {

        var a = true

        if(model_et.text.isNullOrEmpty())
        {
            a = false
            model_et.error = "Required"
        }

        if(odometer_et.text.isNullOrEmpty())
        {
            a = false
            odometer_et.error = "Required"
        }

        if(engine_no_et.text.isNullOrEmpty())
        {
            a = false
            engine_no_et.error = "Required"
        }

        if(color_et.text.isNullOrEmpty())
        {
            a = false
            color_et.error = "Required"
        }

        if(chasis_et.text.isNullOrEmpty())
        {
            a = false
            chasis_et.error = "Required"
        }


        if(covb.text.isNullOrEmpty())
        {
            a = false
            covb.error = "Required"
        }

        if(cove.text.isNullOrEmpty())
        {
            a = false
            cove.error = "Required"
        }

        if(cot.text.isNullOrEmpty())
        {
            a = false
            cot.error = "Required"
        }

        if(remarks_et.text.isNullOrEmpty())
        {
            a = false
            remarks_et.error = "Required"
        }

        return a
    }

    private fun start_timer() {
         timer = object : CountDownTimer(480000, 1000) {
            // adjust the milli seconds here
            @SuppressLint("DefaultLocale", "SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                Timer_tv.setText( "Time Left\n" + String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    millisUntilFinished))))
            }

            override fun onFinish() {

                Toast.makeText(applicationContext , "Time Over, Form Not Submitted !" , Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext , MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()

    }

    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            val locationRequest = LocationRequest()
            locationRequest.setInterval(2000)
            locationRequest.setFastestInterval(1000)
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback!!, null)
        }
    }

    private fun getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(
                this, "Can't find current address, ",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val intent = Intent(this, GetAddressIntentService::class.java)
        intent.putExtra("add_receiver", addressResultReceiver)
        intent.putExtra("add_location", currentLocation)
        startService(intent)
    }

    inner class LocationAddressResultReceiver internal constructor(handler: Handler?, fillform: carfillform) :
        ResultReceiver(handler) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            if (resultCode == 0) {
                Log.d("Address", "Location null retrying")
                getAddress()
            }
            if (resultCode == 1) {
                Toast.makeText(carfillform(), "Address not found, ", Toast.LENGTH_SHORT).show()
            }
            val currentAdd = resultData.getString("address_result")
            if (currentAdd != null) {
                showResults(currentAdd)
            }
        }
    }

    private fun showResults(currentAdd: String) {
        Location_tv.text = currentAdd
        Loaction_pg_bar.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient!!.removeLocationUpdates(locationCallback!!)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
            else {
                Toast.makeText(this, "Location permission not granted, " + "restart the app if you want the feature", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        timer!!.cancel()
    }
}