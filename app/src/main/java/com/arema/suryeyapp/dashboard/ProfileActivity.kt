package com.arema.suryeyapp.dashboard

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.arema.suryeyapp.LoginActivity
import com.arema.suryeyapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    val db = FirebaseDatabase.getInstance()
    val refrence = db.reference.child("Users")
    var profilepiclink  = "empty"
    var strrefrence : StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        strrefrence = FirebaseStorage.getInstance().reference

        val progresdialog = ProgressDialog(this)
        progresdialog.setTitle("Loading Data!!")
        progresdialog.show()

        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , "")).child("userdata")
            .child("name").get().addOnSuccessListener {

                profile_name.text = it.value.toString()

            }.addOnFailureListener{

                Toast.makeText(this, "Fetching Data Failed !" , Toast.LENGTH_SHORT).show()
            }

        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , "")).child("userdata")
            .child("phone").get().addOnSuccessListener {

                profile_PhoneNumber.text = it.value.toString()

            }.addOnFailureListener{

                Toast.makeText(this, "Fetching Data Failed !" , Toast.LENGTH_SHORT).show()
            }

        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , "")).child("userdata")
            .child("address").get().addOnSuccessListener {

                profile_Address.text = it.value.toString()

            }.addOnFailureListener{

                Toast.makeText(this, "Fetching Data Failed !" , Toast.LENGTH_SHORT).show()
            }

        refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , "")).child("userdata")
            .child("profilepic").get().addOnSuccessListener {

                if(it.value.toString() == "uploded")
                {
                    val photoReference = strrefrence!!.child("images/${FirebaseAuth.getInstance().currentUser?.email}/ Profilepic")

                    val ONE_MEGABYTE = (1024 * 1024).toLong()
                    photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
                        val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        profile_pic_img.setImageBitmap(bmp)
                        progresdialog.dismiss()
                    }.addOnFailureListener {
                        Toast.makeText(
                            applicationContext,
                            "No Such file or Path found!!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else
                {
                    progresdialog.dismiss()
                }
            }.addOnFailureListener {

            }





        profile_back_btn.setOnClickListener {
            super.onBackPressed()
            finish()
        }

        image_edit_btn.setOnClickListener {
            selectimage( 100 )
        }

        profile_logout_btn_down.setOnClickListener {
            if(FirebaseAuth.getInstance().currentUser != null) {
                FirebaseAuth.getInstance().signOut()
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }
    }


    private fun selectimage(i: Int) {

        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent , i)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode == 100 && data != null )
        {

            profilepiclink = data.dataString.toString()

            profile_pic_img.setImageURI(profilepiclink.toUri())
            uploadimage(100)
        }

    }

    private fun uploadimage(i: Int) {

        val progresdialog = ProgressDialog(this)
        progresdialog.setTitle("Uploading Image")
        progresdialog.show()

        if (i == 100) {

            strrefrence = FirebaseStorage.getInstance().getReference("images/${FirebaseAuth.getInstance().currentUser?.email}/ Profilepic")
            strrefrence?.putFile(profilepiclink.toUri())?.addOnCompleteListener {
                Toast.makeText(this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show()
                refrence.child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
                    .child("profilepic").setValue("uploded")
                if(progresdialog.isShowing)
                    progresdialog.dismiss()
            }?.addOnFailureListener {
                Toast.makeText(this , "${it}", Toast.LENGTH_SHORT).show()
                if(progresdialog.isShowing)
                    progresdialog.dismiss()
            }

        }
    }

}