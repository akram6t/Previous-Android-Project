package com.example.kotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<CModels>()
        list.add(CModels("googiehost","UsernameAndPassword"))
        list.add(CModels("googiehost","UsernameAndPassword"))
        list.add(CModels("googiehost","UsernameAndPassword"))
        list.add(CModels("googiehost","UsernameAndPassword"))
        list.add(CModels("googiehost","UsernameAndPassword"))
        var adapter:CAdapter? = null
        adapter = CAdapter(this,list)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

    }
}