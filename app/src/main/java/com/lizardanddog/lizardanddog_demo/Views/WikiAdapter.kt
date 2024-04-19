package com.lizardanddog.lizardanddog_demo.Views

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.bumptech.glide.Glide
import com.lizardanddog.lizardanddog_demo.Models.WikiModel
import com.lizardanddog.lizardanddog_demo.R
import com.lizardanddog.lizardanddog_demo.ViewModels.WikiViewModel

class WikiAdapter(private val context: Context, private val wikiViewModel: WikiViewModel, private val requestQueue: RequestQueue) : RecyclerView.Adapter<WikiViewHolder>() {

    private var wikiList: List<WikiModel> = emptyList()

    fun submitList(data: List<WikiModel>) {
        wikiList = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WikiViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.wiki_item, parent, false)
        return WikiViewHolder(view)
    }

    override fun onBindViewHolder(holder: WikiViewHolder, position: Int) {
        val wikiModel = wikiList[position]
        holder.title.text = wikiModel.title
        // Preset the holder.thumbnail size to ensure placeholder is adequately sized...
        val layoutParams = holder.thumbnail.layoutParams
        layoutParams.width = 200
        layoutParams.height = 200
        holder.thumbnail.layoutParams = layoutParams
        // Get the image data using glide
        // TODO: change size to a relative size to the screen
        Glide.with(context)
            .load(wikiModel.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.lizardogbw)
            .override(200, 200)
            .error(Glide.with(context)
                .load(R.drawable.lizardogbw)
                .override( 200,  200)
                .centerCrop())
            .into(holder.thumbnail)

       // Load the elements in advance to ensure smoother loading
      if (position == (wikiList.size-1)-50){
          wikiViewModel.fetchData("Wine", requestQueue )
      }


    }

    override fun getItemCount(): Int {
        return wikiList.size
    }
}