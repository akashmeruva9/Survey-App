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
import kotlinx.android.synthetic.main.activity_buildinfo.*

class buildinfo : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildinfo)

        val key = intent.getStringExtra("key").toString()

        val dialog = ProgressDialog(this)
        dialog.setTitle("Loading...")
        dialog.setCancelable(false)
        dialog.show()

        db = FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace(".", ""))
            .child("completedsurveys")

        db.child(key).child("surveydata").child("address").get().addOnSuccessListener {
            val data = it.value
            buildinfo_Location_tv.text = data.toString()
        }
        db.child(key).child("surveydata").child("agentname").get().addOnSuccessListener {
            val data = it.value
            agentname.text = data.toString()
        }
        db.child(key).child("surveydata").child("agentnumber").get().addOnSuccessListener {
            val data = it.value
            agentnumber.text = data.toString()
        }
        db.child(key).child("surveydata").child("ageofproperty").get().addOnSuccessListener {
            val data = it.value
            ageofproperty.text = data.toString()
        }
        db.child(key).child("surveydata").child("arealengthl").get().addOnSuccessListener {
            val data = it.value
            lengthl.text = data.toString()
        }
        db.child(key).child("surveydata").child("arealengthr").get().addOnSuccessListener {
            val data = it.value
            lengthr.text = data.toString()
        }
        db.child(key).child("surveydata").child("areawidthf").get().addOnSuccessListener {
            val data = it.value
            widthf.text = data.toString()
        }
        db.child(key).child("surveydata").child("areawidthr").get().addOnSuccessListener {
            val data = it.value
            widthr.text = data.toString()
        }
        db.child(key).child("surveydata").child("bankname").get().addOnSuccessListener {
            val data = it.value
            bankname_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("brokername").get().addOnSuccessListener {
            val data = it.value
            brokername.text = data.toString()
        }
        db.child(key).child("surveydata").child("brokernumber").get().addOnSuccessListener {
            val data = it.value
            brokernumber.text = data.toString()
        }
        db.child(key).child("surveydata").child("eastboundary").get().addOnSuccessListener {
            val data = it.value
            east.text = data.toString()
        }
        db.child(key).child("surveydata").child("floorofproperty").get().addOnSuccessListener {
            val data = it.value
            floorproperty.text = data.toString()
        }
        db.child(key).child("surveydata").child("landmark").get().addOnSuccessListener {
            val data = it.value
            landmark_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("listofocuupants").get().addOnSuccessListener {
            val data = it.value
            listofoccupants.text = data.toString()
        }
        db.child(key).child("surveydata").child("locality").get().addOnSuccessListener {
            val data = it.value
            locality_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("northboundary").get().addOnSuccessListener {
            val data = it.value
            north.text = data.toString()
        }
        db.child(key).child("surveydata").child("numberoffloors").get().addOnSuccessListener {
            val data = it.value
            numberoffloors.text = data.toString()
        }
        db.child(key).child("surveydata").child("numberoflifts").get().addOnSuccessListener {
            val data = it.value
            numberoflifts.text = data.toString()
        }
        db.child(key).child("surveydata").child("occupancy").get().addOnSuccessListener {
            val data = it.value
            occupancy_status_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("propertyrate").get().addOnSuccessListener {
            val data = it.value
            rateofproperty.text = data.toString()
        }
        db.child(key).child("surveydata").child("propertydemarcation").get().addOnSuccessListener {
            val data = it.value
            propertydemarcatio_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("rescom").get().addOnSuccessListener {
            val data = it.value
            usage_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("road").get().addOnSuccessListener {
            val data = it.value
            typeofroad_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("roadwidth").get().addOnSuccessListener {
            val data = it.value
            widthofroad.text = data.toString()
        }
        db.child(key).child("surveydata").child("shape").get().addOnSuccessListener {
            val data = it.value
            shapeofproperty_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("southboundary").get().addOnSuccessListener {
            val data = it.value
            south.text = data.toString()
        }
        db.child(key).child("surveydata").child("totalarea").get().addOnSuccessListener {
            val data = it.value
            totalarea.text = data.toString()
        }
        db.child(key).child("surveydata").child("unitareabup").get().addOnSuccessListener {
            val data = it.value
            bup.text = data.toString()
        }
        db.child(key).child("surveydata").child("unitareacarpet").get().addOnSuccessListener {
            val data = it.value
            carpet.text = data.toString()
        }
        db.child(key).child("surveydata").child("unitareasbup").get().addOnSuccessListener {
            val data = it.value
            sbup.text = data.toString()
        }
        db.child(key).child("surveydata").child("usage").get().addOnSuccessListener {
            val data = it.value
            usage_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("wardnumber").get().addOnSuccessListener {
            val data = it.value
            wardnumber_et.text = data.toString()
        }
        db.child(key).child("surveydata").child("westboundary").get().addOnSuccessListener {
            val data = it.value
            west.text = data.toString()
        }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("4F")
            .child("numberofKitchen").get().addOnSuccessListener {
            val data = it.value
            noofkitchen4f.text = data.toString()
        }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("4F")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroom4f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("4F")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoilet4f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("4F")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhall4f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("4F")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstruction4f.text = data.toString()
            }

        db.child(key).child("surveydata")
            .child("Area of Construction").child("5F")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchen5f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("5F")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroom5f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("5F")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoilet5f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("5F")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhall5f.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("5F")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstruction5f.text = data.toString()
            }


        db.child(key).child("surveydata")
            .child("Area of Construction").child("BF")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchenbf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("BF")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroombf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("BF")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoiletbf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("BF")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhallbf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("BF")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructionbf.text = data.toString()
            }


        db.child(key).child("surveydata")
            .child("Area of Construction").child("FF")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchenff.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("FF")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroomff.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("FF")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoiletff.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("FF")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhallff.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("FF")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructionff.text = data.toString()
            }


        db.child(key).child("surveydata")
            .child("Area of Construction").child("GF")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchengf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("GF")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroomgf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("GF")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoiletgf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("GF")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhallgf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("GF")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructiongf.text = data.toString()
            }



        db.child(key).child("surveydata")
            .child("Area of Construction").child("MZ")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchenmz.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("MZ")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroommz.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("MZ")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoiletmz.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("MZ")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhallmz.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("MZ")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructionmz.text = data.toString()
            }

        db.child(key).child("surveydata")
            .child("Area of Construction").child("SF")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchensf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("SF")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroomsf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("SF")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoiletsf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("SF")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhallsf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("SF")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructionsf.text = data.toString()
            }



        db.child(key).child("surveydata")
            .child("Area of Construction").child("TF")
            .child("numberofKitchen").get().addOnSuccessListener {
                val data = it.value
                noofkitchentf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("TF")
            .child("numberofRoom").get().addOnSuccessListener {
                val data = it.value
                noofroomtf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("TF")
            .child("numberofToilet").get().addOnSuccessListener {
                val data = it.value
                nooftoilettf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("TF")
            .child("numberofhall").get().addOnSuccessListener {
                val data = it.value
                noofhalltf.text = data.toString()
            }
        db.child(key).child("surveydata")
            .child("Area of Construction").child("TF")
            .child("totalArea").get().addOnSuccessListener {
                val data = it.value
                totalareaofconstructiontf.text = data.toString()
                dialog.dismiss()
            }


        buildinfo_backbtn.setOnClickListener {
            var intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this , MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}