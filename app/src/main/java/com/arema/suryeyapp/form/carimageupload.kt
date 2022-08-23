package com.arema.suryeyapp.form

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_carimageupload.*
import java.util.*


class carimageupload : AppCompatActivity() {

    private lateinit var key : String
    private lateinit var sp : SharedPreferences
    private lateinit var db : FirebaseDatabase
    private lateinit var refrence : DatabaseReference
    var strrefrence : StorageReference? = null
    var imgstring = "empty"
    var ass : ImageView? = null
    var numberofpics = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carimageupload)

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


        numberplatepic.setOnClickListener {

            ass = numberplatepic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        selfienumberplate.setOnClickListener {

            ass = selfienumberplate

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontpic.setOnClickListener {

            ass = frontpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontrightpic.setOnClickListener {

            ass = frontrightpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        rightsidepic.setOnClickListener {

            ass = rightsidepic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        rearrightpic.setOnClickListener {

            ass = rearrightpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        backside.setOnClickListener {

            ass = backside

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        rearleftpic.setOnClickListener {

            ass = rearleftpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        leftside.setOnClickListener {

            ass = leftside

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontleft.setOnClickListener {

            ass = frontleft

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        erwn.setOnClickListener {

            ass = erwn

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        erls.setOnClickListener {

            ass = erls

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        engineroomright.setOnClickListener {

            ass = engineroomright

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        chasisnumber.setOnClickListener {

            ass = chasisnumber

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        enginechasis.setOnClickListener {

            ass = enginechasis

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        dashboardpic.setOnClickListener {

            ass = dashboardpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        gearstick.setOnClickListener {

            ass = gearstick

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        odometerpic.setOnClickListener {

            ass = odometerpic

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frhdi.setOnClickListener {

            ass = frhdi

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        frontback.setOnClickListener {

            ass = frontback

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        dicky.setOnClickListener {

            ass = dicky

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        tfr.setOnClickListener {

            ass = tfr

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        trr.setOnClickListener {

            ass = trr

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        trl.setOnClickListener {

            ass = trl

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        tfl.setOnClickListener {

            ass = tfl

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        stepney.setOnClickListener {

            ass = stepney

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        rcbook.setOnClickListener {

            ass = rcbook

            ImagePicker.with(this)
                .cameraOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        submut_survey_button.setOnClickListener {
            if(numberofpics<27)
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

                        val intent = Intent(this, carfillform::class.java)
                        intent.putExtra("key", key)
                        startActivity(intent)
                        this.finish()

                    }
            }
        }

        carimage_back_btn.setOnClickListener {
                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()

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