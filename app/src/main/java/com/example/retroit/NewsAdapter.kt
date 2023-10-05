package com.example.retroit

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context,private val newsList: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {


    //must extend ViewHolder class to become a viewholder itself
    //holds the views
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.newsImage)
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val description: TextView = itemView.findViewById(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)

        return  NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        val article: Article = newsList[position]

        holder.title.setText(newsList[position].title)
        holder.description.setText(newsList[position].description)
        Glide.with(context).load(article.urlToImage).into(holder.image)

    }
}