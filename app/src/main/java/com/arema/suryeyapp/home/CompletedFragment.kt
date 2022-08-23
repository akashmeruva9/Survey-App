package com.arema.suryeyapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arema.suryeyapp.R
import com.arema.suryeyapp.SueveyData
import com.arema.suryeyapp.form.buildinfo
import com.arema.suryeyapp.form.carinfo
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_completed.*


class CompletedFragment : Fragment(R.layout.fragment_completed) {
    private lateinit var db: DatabaseReference
    private lateinit var adapter : completedadapter
    private  var list : ArrayList<SueveyData> = ArrayList()

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("completedsurveys")

        completed_recycler_view.setHasFixedSize(true)
        completed_recycler_view.layoutManager = LinearLayoutManager(context)

        val options: FirebaseRecyclerOptions<SueveyData?> = FirebaseRecyclerOptions.Builder<SueveyData>()
            .setQuery(db, SueveyData::class.java)
            .build()

        adapter = completedadapter(options , this)
        completed_recycler_view.setAdapter(adapter)

    }

    fun onclicked(type : String, key: String)
    {
        if(type == "car")
        {
            val intent = Intent(context , carinfo::class.java)
            intent.putExtra("key" , key)
            startActivity(intent)
        }else if(type == "build")
        {
            val intent = Intent(context , buildinfo::class.java)
            intent.putExtra("key" , key)
            startActivity(intent)
        }

    }
}