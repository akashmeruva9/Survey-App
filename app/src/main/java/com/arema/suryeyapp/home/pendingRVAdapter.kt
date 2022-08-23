package com.arema.suryeyapp.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arema.suryeyapp.R
import com.arema.suryeyapp.SueveyData
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


internal class personAdapter(options: FirebaseRecyclerOptions<SueveyData?>, private val listener : PendingFragment) : FirebaseRecyclerAdapter<SueveyData, personAdapter.personsViewholder>(options) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: personsViewholder, position: Int, model: SueveyData)
     {

        holder.name.setText(model.getKey())

        holder.date.setText("${model.getDate()}  ${model.getTime()}")

        if(model.type == "car") {
            holder.img.setImageResource(R.drawable.car_logo)
            holder.cv.setBackgroundColor(Color.parseColor("#EF9F9F"))

        } else if(model.type == "build") {
            holder.img.setImageResource(R.drawable.building_logo)
            holder.cv.setBackgroundColor(Color.parseColor("#FAD4D4"))
        }

         holder.details.setOnClickListener {
             listener.ondetailsclicked(model.key, model.type)
         }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): personsViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.survey_item, parent, false)
        return personsViewholder(view)
    }


    internal inner class personsViewholder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var date: TextView
        var img: ImageView
        var details : Button
        var cv : ConstraintLayout

        init {
            name = itemView.findViewById(R.id.survey_item_tv1)
            date = itemView.findViewById(R.id.survey_item_tv2)
            img = itemView.findViewById(R.id.survey_item_img)
            details = itemView.findViewById(R.id.survry_item_details_btn)
            cv = itemView.findViewById(R.id.cv_pending_ad)
        }
    }
}

interface detailsclick{
    fun ondetailsclicked(item : String)
}

