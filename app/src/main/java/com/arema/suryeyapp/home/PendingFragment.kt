package com.arema.suryeyapp.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arema.suryeyapp.form.Details
import com.arema.suryeyapp.R
import com.arema.suryeyapp.SueveyData
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_pending.*


class PendingFragment : Fragment(R.layout.fragment_pending) {

    private lateinit var db: DatabaseReference
    private lateinit var adapter : personAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = FirebaseDatabase.getInstance().reference.child("Users")
            .child((FirebaseAuth.getInstance().currentUser?.email).toString().replace("." , ""))
            .child("pendingsurveys")

        pending_recycler_view.setHasFixedSize(true)
        pending_recycler_view.layoutManager = LinearLayoutManager(context)

        val options: FirebaseRecyclerOptions<SueveyData?> = FirebaseRecyclerOptions.Builder<SueveyData>()
            .setQuery(db, SueveyData::class.java)
            .build()

        adapter = personAdapter(options , this)
       pending_recycler_view.setAdapter(adapter)

    }

    fun ondetailsclicked(item: String, type: String)
    {
        val intent = Intent(context , Details::class.java)
        intent.putExtra("key" , item)
        intent.putExtra("type" , type)
        startActivity(intent)
    }
}