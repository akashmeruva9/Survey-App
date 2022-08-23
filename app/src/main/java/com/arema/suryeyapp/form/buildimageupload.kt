package com.arema.suryeyapp.form

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import com.arema.suryeyapp.R
import com.arema.suryeyapp.home.MainActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_buildimageupload.*
import java.util.*

class buildimageupload : AppCompatActivity() {

    private lateinit var key : String
    private lateinit var sp : SharedPreferences
    private lateinit var db : FirebaseDatabase
    private lateinit var refrence : DatabaseReference
    var strrefrence : StorageReference? = null
    var imgstring = "empty"
    var ass : ImageView? = null
    var numberofpics = 0
    var type = "opt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildimageupload)

        sp = this.getSharedPreferences("userdetails", MODE_PRIVATE)
        key = intent.getStringExtra("key").toString()
        db = FirebaseDatabase.getInstance()
        refrence = db.reference.child("Users")
        strrefrence = FirebaseStorage.getInstance().reference

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minutes = c.get(Calendar.MINUTE)

        var ampm: String? = null
        var myHour = 0

        if(hour > 12)
        {
            myHour = hour - 12
            ampm = "PM"
        }else if(hour == 12)
        {
            myHour = hour
            ampm = "PM"
        }else
        {
            myHour = hour
            ampm = "AM"
        }

        val date = "$day/${month+1}/$year"
        val time = "$myHour:$minutes $ampm"


        buildimage_back_btn.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        submit_survey_button_build.setOnClickListener {
            if(numberofpics<10)
            {
                Toast.makeText(this, "Please Upload all the Pics" , Toast.LENGTH_SHORT).show()
            }else
            {
                val progresdialog = ProgressDialog(this)
                progresdialog.setTitle("Updating Survey")
                progresdialog.show()

                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key).child("date").setValue(date)
                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key).child("time").setValue(time)
                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
                    .child("pendingsurveys").child(key)
                    .child("status").setValue("imguploaded").addOnSuccessListener {

                        val intent = Intent(this, buildfillform::class.java)
                        intent.putExtra("key", key)
                        startActivity(intent)
                        this.finish()

                    }

            }
        }

        locationgraph.setOnClickListener {

            ass = locationgraph
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        googlemaps.setOnClickListener {

            ass = googlemaps
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        streetview.setOnClickListener {

            ass = streetview
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        engineer.setOnClickListener {

            ass = engineer
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        person.setOnClickListener {

            ass = person
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        maindoor.setOnClickListener {

            ass = maindoor
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontview.setOnClickListener {

            ass = frontview
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        leftsideview.setOnClickListener {

            ass = leftsideview
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        rightside.setOnClickListener {

            ass = rightside
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontroad.setOnClickListener {

            ass = frontroad
            type = "re"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        externalpic1.setOnClickListener {

            ass = externalpic1
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        externalpic2.setOnClickListener {

            ass = externalpic2
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic1l.setOnClickListener {

            ass = internalpic1l
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic2k.setOnClickListener {

            ass = internalpic2k
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic3b.setOnClickListener {

            ass = internalpic3b
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic1.setOnClickListener {

            ass = internalpic1
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic2.setOnClickListener {

            ass = internalpic2
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic3.setOnClickListener {

            ass = internalpic3
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        internalpic4.setOnClickListener {

            ass = internalpic4
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        supportdocs.setOnClickListener {

            ass = supportdocs
            type = "opt"

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val uri: Uri = data?.data!!
            imgstring = data.dataString.toString()
            uploadimage(100)

        } else if(resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadimage(i: Int) {

        val progresdialog = ProgressDialog(this)
        progresdialog.setTitle("Uploading Image")
        progresdialog.show()

        if (i == 100) {

            strrefrence = FirebaseStorage.getInstance().getReference("images/${FirebaseAuth.getInstance().currentUser?.email}/ ${key} / ${ass?.id}")
            strrefrence?.putFile(imgstring.toUri())?.addOnCompleteListener {
                Toast.makeText(this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show()

                if(type == "re")
                numberofpics++

                ass?.setImageURI(Uri.parse(imgstring.toUri().toString()))
                ass!!.isActivated = false
                ass!!.isClickable = false
                ass!!.isEnabled = false
                if(progresdialog.isShowing)
                    progresdialog.dismiss()
            }?.addOnFailureListener {
                Toast.makeText(this, "${it}", Toast.LENGTH_SHORT).show()
                if(progresdialog.isShowing)
                    progresdialog.dismiss()
            }

        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()

    }


}