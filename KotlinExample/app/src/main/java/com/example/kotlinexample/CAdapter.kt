package com.example.kotlinexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CAdapter (val context: Context, val list:ArrayList<CModels>):
    RecyclerView.Adapter<CAdapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title:TextView = itemView.findViewById(R.id.rtitle)
        val notes:TextView = itemView.findViewById(R.id.rnotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.crecyclerview,null)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.notes.text = list[position].notes
        holder.title.setOnClickListener {
            Toast.makeText(context,"titleclicked",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}