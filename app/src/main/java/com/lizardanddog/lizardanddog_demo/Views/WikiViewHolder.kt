package com.lizardanddog.lizardanddog_demo.Views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lizardanddog.lizardanddog_demo.R

class WikiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.title)
    var thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
}