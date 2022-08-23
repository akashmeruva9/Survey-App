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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arema.suryeyapp.areadetails
import com.arema.suryeyapp.builddetails
import com.arema.suryeyapp.GetAddressIntentService
import com.arema.suryeyapp.R
import com.arema.suryeyapp.home.MainActivity
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_buildfillform.*


class buildfillform : AppCompatActivity() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 2
    private var addressResultReceiver: buildfillform.LocationAddressResultReceiver? = null
    private var currentLocation: Location? = null
    private var locationCallback: LocationCallback? = null
    private lateinit var key : String
    private lateinit var sp : SharedPreferences
    var properydemarcation = "NO"
    var usage = "Select"
    var occupancy = "Select"
    var shape = "Select"
    var locality = "Select"
    var road = "Select"
    var rescom = "Select"
    var timer:CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildfillform)


        sp = this.getSharedPreferences("userdetails", MODE_PRIVATE)
        key = intent.getStringExtra("key").toString()
        val db = FirebaseDatabase.getInstance()
        val refrence = db.reference.child("Users")

        start_timer()
        addressResultReceiver = LocationAddressResultReceiver(Handler())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                currentLocation = locationResult.locations[0]
                getAddress()
            }
        }
        startLocationUpdates()


        buildfillform_back_btn.setOnClickListener {

            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val AdapterUsage = ArrayAdapter.createFromResource(this , R.array.array_usage, R.layout.spinner_layout)
        AdapterUsage.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_usage.adapter = AdapterUsage

        spinner_usage.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                usage = spinner_usage.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        val Adapterocuupancy = ArrayAdapter.createFromResource(this, R.array.array_occupancystatus, R.layout.spinner_layout)
        Adapterocuupancy.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_occupancy.adapter = Adapterocuupancy

        spinner_occupancy.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                occupancy = spinner_occupancy.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val AdapterShape = ArrayAdapter.createFromResource(this, R.array.array_shape, R.layout.spinner_layout)
        AdapterShape.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_shapeofproperty.adapter = AdapterShape

        spinner_shapeofproperty.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                shape = spinner_shapeofproperty.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val AdapterLocality = ArrayAdapter.createFromResource(this, R.array.array_locality, R.layout.spinner_layout)
        AdapterLocality.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_locality.adapter = AdapterLocality

        spinner_locality.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                locality = spinner_locality.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        val AdapterRoad = ArrayAdapter.createFromResource(this, R.array.array_road, R.layout.spinner_layout)
        AdapterRoad.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_typeofroad.adapter = AdapterRoad

        spinner_typeofroad.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                road = spinner_typeofroad.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val AdapterRescom = ArrayAdapter.createFromResource(this, R.array.array_rescom, R.layout.spinner_layout)
        AdapterRescom.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)

        spinner_residetialcommercial.adapter = AdapterRescom

        spinner_residetialcommercial.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                rescom = spinner_residetialcommercial.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        continue_survey_btn_build.setOnClickListener {
            if(checkdata() == true )
            {
                val progresdialog = ProgressDialog(this)
                progresdialog.setTitle("Updating Data!!")
                progresdialog.setCancelable(false)
                progresdialog.show()

                val bankname = bankname_et.text.toString()
                val address = Location_tv.text.toString()
                val landmark = landmark_et.text.toString()
                val wardnumber = wardnumber_et.text.toString()
                usage
                occupancy
                val listofocuupants = listofoccupants.text.toString()
                val northboundary  = north.text.toString()
                val southboundary = south.text.toString()
                val eastboundary = east.text.toString()
                val westboundary = west.text.toString()
                shape
                locality
                val areawidthf = widthf.text.toString()
                val areawidthr = widthr.text.toString()
                val arealengthl = lengthl.text.toString()
                val arealengthr = lengthr.text.toString()
                val Totalarea = totalarea.text.toString()
                val numberoffloors = numberoffloors.text.toString()
                val floorofproperty = floorproperty.text.toString()
                val numberoflifts = numberoflifts.text.toString()
                val ageofproperty = ageofproperty.text.toString()
                properydemarcation
                road
                val roadwidth = widthofroad.text.toString()
                rescom
                val unitareacarpet = carpet.text.toString()
                val unitareabup = bup.text.toString()
                val unitareasbup = sbup.text.toString()
                val propertyrate = rateofproperty.text.toString()
                val brokername = brokername.text.toString()
                val brokernumber = brokernumber.text.toString()
                val agentname = agentname.text.toString()
                val agentnumber = agentnumber.text.toString()


                val databf = areadetails(noofhallbf.text.toString(),
                    noofroombf.text.toString(),
                    noofkitchenbf.text.toString(),
                    nooftoiletbf.text.toString(),
                    totalareaofconstructionbf.text.toString())

                val datamz = areadetails(noofhallmz.text.toString(),
                    noofroommz.text.toString(),
                    noofkitchenmz.text.toString(),
                    nooftoiletmz.text.toString(),
                    totalareaofconstructionmz.text.toString())

                val datagf = areadetails(noofhallgf.text.toString(),
                    noofroomgf.text.toString(),
                    noofkitchengf.text.toString(),
                    nooftoiletgf.text.toString(),
                    totalareaofconstructiongf.text.toString())

                val dataff = areadetails(noofhallff.text.toString(),
                    noofroomff.text.toString(),
                    noofkitchenff.text.toString(),
                    nooftoiletff.text.toString(),
                    totalareaofconstructionff.text.toString())

                val datasf = areadetails(noofhallsf.text.toString(),
                    noofroomsf.text.toString(),
                    noofkitchensf.text.toString(),
                    nooftoiletsf.text.toString(),
                    totalareaofconstructionsf.text.toString())

                val datatf = areadetails(noofhalltf.text.toString(),
                    noofroomtf.text.toString(),
                    noofkitchentf.text.toString(),
                    nooftoilettf.text.toString(),
                    totalareaofconstructiontf.text.toString())

                val data4f = areadetails(noofhall4f.text.toString(),
                    noofroom4f.text.toString(),
                    noofkitchen4f.text.toString(),
                    nooftoilet4f.text.toString(),
                    totalareaofconstruction4f.text.toString())

                val data5f = areadetails(noofhall5f.text.toString(),
                    noofroom5f.text.toString(),
                    noofkitchen5f.text.toString(),
                    nooftoilet5f.text.toString(),
                    totalareaofconstruction5f.text.toString())


                val data = builddetails(bankname, address, landmark, wardnumber,
                    usage, occupancy, listofocuupants, northboundary, southboundary,
                    eastboundary, westboundary, shape, locality, areawidthf, areawidthr,
                    arealengthl, arealengthr, Totalarea, numberoffloors, floorofproperty,
                    numberoflifts, ageofproperty, properydemarcation, road, roadwidth, rescom,
                    unitareacarpet, unitareabup, unitareasbup, propertyrate, brokername, brokernumber,
                    agentname, agentnumber)


                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key)
                    .child("status").setValue("completed")

                refrence.child(
                    (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key).child("surveydata")
                    .setValue(data).addOnSuccessListener {


                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("BF")
                            .setValue(databf)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("MZ")
                            .setValue(datamz)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("GF")
                            .setValue(datagf)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("FF")
                            .setValue(dataff)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("SF")
                            .setValue(datasf)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("TF")
                            .setValue(datatf)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("4F")
                            .setValue(data4f)

                        refrence.child(
                            (FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                            .child("pendingsurveys").child(key).child("surveydata")
                            .child("Area of Construction")
                            .child("5F")
                            .setValue(data5f).addOnSuccessListener {

                                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                                    .child("pendingsurveys").child(key).get().addOnSuccessListener {

                                        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                                            .child("completedsurveys").child(key).setValue(it.value)
                                            .addOnSuccessListener {

                                                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                                                    .child("pendingsurveys").child(key).removeValue()
                                                    .addOnSuccessListener {
                                                        progresdialog.dismiss()

                                                        val a = sp.getInt("completed", 999)

                                                        if (sp.getInt("completed", 999) != 999 && sp.getInt("completed", 999) != null) {
                                                            val editor = sp.edit()
                                                            editor.putInt("completed", a + 1)
                                                            editor.apply() }

                                                        val a1 = sp.getInt("completedw", 999)

                                                        if (sp.getInt("completedw", 999) != 999 && sp.getInt("completedw", 999) != null) {
                                                            val editor = sp.edit()
                                                            editor.putInt("completedw", a1 + 1)
                                                            editor.apply() }

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
        }


    }


    private fun start_timer() {
       timer =  object : CountDownTimer(480000, 1000) {
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

    inner class LocationAddressResultReceiver internal constructor(handler: Handler?) :
        ResultReceiver(handler) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            if (resultCode == 0) {
                Log.d("Address", "Location null retrying")
                getAddress()
            }
            if (resultCode == 1) {
                Toast.makeText(buildfillform(), "Address not found, ", Toast.LENGTH_SHORT).show()
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

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.rg5r1 -> if (checked) properydemarcation = "NO"
            R.id.rg5r2 -> if (checked) properydemarcation = "YES"
        }
    }


    private fun checkdata(): Any {

        var a = true

        if(bankname_et.text.isNullOrEmpty())
        {
            bankname_et.error = "Required"
            a = false
        }

        if(landmark_et.text.isNullOrEmpty())
        {
            landmark_et.error = "Required"
            a = false
        }

        if(wardnumber_et.text.isNullOrEmpty())
        {
            wardnumber_et.error = "Required"
            a = false
        }

        if(usage == "Select")
        {
            tv5.error = "Required"
            a = false
        }else
        {
            tv5.error = null
        }

        if(occupancy == "Select")
        {
            tv6.error = "Required"
            a = false
        }else
        {
            tv6.error = null
        }

        if(listofoccupants.text.isNullOrEmpty())
        {
            listofoccupants.error = "Required"
            a = false
        }

        if(north.text.isNullOrEmpty())
        {
            north.error = "Required"
            a = false
        }

        if(east.text.isNullOrEmpty())
        {
            east.error = "Required"
            a = false
        }

        if(west.text.isNullOrEmpty())
        {
            west.error = "Required"
            a = false
        }

        if(south.text.isNullOrEmpty())
        {
            south.error = "Required"
            a = false
        }

        if(shape == "Select")
        {
            tv9.error = "Required"
            a = false
        }else
        {
            tv9.error = null
        }

        if(locality == "Select")
        {
            tv10.error = "Required"
            a = false
        }else
        {
            tv10.error = null
        }

        if(widthf.text.isNullOrEmpty())
        {
            widthf.error = "Required"
            a = false
        }

        if(widthr.text.isNullOrEmpty())
        {
            widthr.error = "Required"
            a = false
        }

        if(lengthl.text.isNullOrEmpty())
        {
            lengthl.error = "Required"
            a = false
        }

        if(lengthr.text.isNullOrEmpty())
        {
            lengthr.error = "Required"
            a = false
        }

        if(totalarea.text.isNullOrEmpty())
        {
            totalarea.error = "Required"
            a = false
        }

        if(numberoffloors.text.isNullOrEmpty())
        {
            numberoffloors.error = "Required"
            a = false
        }

        if(floorproperty.text.isNullOrEmpty())
        {
            floorproperty.error = "Required"
            a = false
        }

        if(numberoflifts.text.isNullOrEmpty())
        {
            numberoflifts.error = "Required"
            a = false
        }

        if(ageofproperty.text.isNullOrEmpty())
        {
            ageofproperty.error = "Required"
            a = false
        }

        if(road == "Select")
        {
            tv16.error = "Required"
            a = false
        }else
        {
            tv16.error = null
        }

        if(widthofroad.text.isNullOrEmpty())
        {
            widthofroad.error = "Required"
            a = false
        }

        if(rateofproperty.text.isNullOrEmpty())
        {
            rateofproperty.error = "Required"
            a = false
        }

        if(brokername.text.isNullOrEmpty())
        {
            brokername.error = "Required"
            a = false
        }

        if(brokernumber.text.isNullOrEmpty())
        {
            brokernumber.error = "Required"
            a = false
        }

        if(agentname.text.isNullOrEmpty())
        {
            agentname.error = "Required"
            a = false
        }

        if(agentnumber.text.isNullOrEmpty())
        {
            ageofproperty.error = "Required"
            a = false
        }

        if(agentnumber.text.isNullOrEmpty())
        {
            agentnumber.error = "Required"
            a = false
        }

        if(carpet.text.isNullOrEmpty())
        {
            carpet.error = "Required"
            a = false
        }

        if(bup.text.isNullOrEmpty())
        {
            bup.error = "Required"
            a = false
        }

        if(sbup.text.isNullOrEmpty())
        {
            sbup.error = "Required"
            a = false
        }

        if(rescom == "Select")
        {
            tv21.error = "Required"
            a = false
        }else
        {
            tv21.error = null
        }

        if(!checkdatabf() && !checkdatamz() && !checkdatagf() && !checkdataff() && !checkdatasf() && !checkdatatf() && !checkdata4f() && !checkdata5f())
        {
            a = false
            tv22.error = "Required"
        }else
            tv22.error = null

        return  a
    }

    private fun checkdatabf(): Boolean {

        var a = true

        if(noofhallbf.text.isNullOrEmpty())
            a = false


        if(noofroombf.text.isNullOrEmpty())
            a = false

        if(noofkitchenbf.text.isNullOrEmpty())
            a = false


        if(nooftoiletbf.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructionbf.text.isNullOrEmpty())
            a = false

        return a
    }


    private fun checkdatamz(): Boolean {

        var a = true

        if(noofhallmz.text.isNullOrEmpty())
            a = false


        if(noofroommz.text.isNullOrEmpty())
            a = false

        if(noofkitchenmz.text.isNullOrEmpty())
            a = false


        if(nooftoiletmz.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructionmz.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdatagf(): Boolean {

        var a = true

        if(noofhallgf.text.isNullOrEmpty())
            a = false


        if(noofroomgf.text.isNullOrEmpty())
            a = false

        if(noofkitchengf.text.isNullOrEmpty())
            a = false


        if(nooftoiletgf.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructiongf.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdataff(): Boolean {

        var a = true

        if(noofhallff.text.isNullOrEmpty())
            a = false


        if(noofroomff.text.isNullOrEmpty())
            a = false

        if(noofkitchenff.text.isNullOrEmpty())
            a = false


        if(nooftoiletff.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructionff.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdatasf(): Boolean {

        var a = true

        if(noofhallsf.text.isNullOrEmpty())
            a = false


        if(noofroomsf.text.isNullOrEmpty())
            a = false

        if(noofkitchensf.text.isNullOrEmpty())
            a = false


        if(nooftoiletsf.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructionsf.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdatatf(): Boolean {

        var a = true

        if(noofhalltf.text.isNullOrEmpty())
            a = false


        if(noofroomtf.text.isNullOrEmpty())
            a = false

        if(noofkitchentf.text.isNullOrEmpty())
            a = false


        if(nooftoilettf.text.isNullOrEmpty())
            a = false


        if(totalareaofconstructiontf.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdata4f(): Boolean {

        var a = true

        if(noofhall4f.text.isNullOrEmpty())
            a = false


        if(noofroom4f.text.isNullOrEmpty())
            a = false

        if(noofkitchen4f.text.isNullOrEmpty())
            a = false


        if(nooftoilet4f.text.isNullOrEmpty())
            a = false


        if(totalareaofconstruction4f.text.isNullOrEmpty())
            a = false

        return a
    }

    private fun checkdata5f(): Boolean {

        var a = true

        if(noofhall5f.text.isNullOrEmpty())
            a = false


        if(noofroom5f.text.isNullOrEmpty())
            a = false

        if(noofkitchen5f.text.isNullOrEmpty())
            a = false


        if(nooftoilet5f.text.isNullOrEmpty())
            a = false


        if(totalareaofconstruction5f.text.isNullOrEmpty())
            a = false

        return a
    }
    override fun onDestroy() {
        super.onDestroy()
        timer!!.cancel()
    }

}