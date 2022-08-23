package com.arema.suryeyapp.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arema.suryeyapp.R
import com.arema.suryeyapp.SueveyData
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


internal class completedadapter(options: FirebaseRecyclerOptions<SueveyData?>, private val listener : CompletedFragment) : FirebaseRecyclerAdapter<SueveyData, completedadapter.completedViewholder>(options) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: completedViewholder, position: Int, model: SueveyData) {

        holder.name.setText(model.getKey())
        holder.timedate.text = "${model.date} ${model.time}"

        if (model.type == "car") {
            holder.img.setImageResource(R.drawable.car_logo)
            holder.cv.setBackgroundColor(Color.parseColor("#EF9F9F"))
        } else if (model.type == "build"){
            holder.img.setImageResource(R.drawable.building_logo)
        holder.cv.setBackgroundColor(Color.parseColor("#FAD4D4")) }

        holder.cv.setOnClickListener {
            listener.onclicked(model.type , model.key)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): completedViewholder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.completed_item,  parent, false)
        return completedViewholder(view)
    }


    internal inner class completedViewholder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var img: ImageView
        var timedate : TextView
        var cv : ConstraintLayout

        init {
            name = itemView.findViewById(R.id.completed_item_tv1)
            img = itemView.findViewById(R.id.completed_item_img)
            timedate = itemView.findViewById<TextView>(R.id.completed_item_tv2)
            cv = itemView.findViewById(R.id.cv_completed_ad)
        }
    }

}

interface completedintemclicked{
    fun onclicked(type : String, key: String)
}