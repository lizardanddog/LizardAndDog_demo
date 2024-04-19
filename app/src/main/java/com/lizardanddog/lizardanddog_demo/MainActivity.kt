package com.lizardanddog.lizardanddog_demo

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.lizardanddog.lizardanddog_demo.ViewModels.WikiViewModel
import com.lizardanddog.lizardanddog_demo.Views.WikiAdapter

class MainActivity : AppCompatActivity() {

    lateinit var wikiListRecyclerView: RecyclerView
    lateinit var wikiAdapter:WikiAdapter
    private lateinit var wikiViewModel: WikiViewModel
    lateinit var requestQueue:RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestQueue=Volley.newRequestQueue(this)
        wikiViewModel = ViewModelProvider(this).get(WikiViewModel::class.java)
        wikiListRecyclerView = findViewById(R.id.rvWikiData)
        wikiAdapter = WikiAdapter(this, wikiViewModel, requestQueue)
        wikiListRecyclerView.adapter=wikiAdapter
        wikiListRecyclerView.layoutManager = LinearLayoutManager(this)
        wikiViewModel.wikiModelsList.observe(this, Observer { list ->
           wikiAdapter.submitList(list)
        })
        wikiViewModel.fetchData("Wine", requestQueue )
    }
}