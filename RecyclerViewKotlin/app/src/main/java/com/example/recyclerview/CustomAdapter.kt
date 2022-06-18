package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val context:Context , val list:ArrayList<CustomObject>)
                : RecyclerView.Adapter<CustomAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context)
             .inflate(R.layout.layout_sample_recyclerview,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val CustomObject = list[position]
        holder.title.text = CustomObject.title
        holder.logo.setImageResource(CustomObject.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById(R.id.textView)
        val logo:ImageView = itemView.findViewById(R.id.imageView)
    }
}