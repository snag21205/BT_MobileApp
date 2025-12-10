package com.example.buoi8.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.buoi8.R
import com.example.buoi8.model.Movie

class MovieAdapter(context: Context, private val movies: List<Movie>) :
    ArrayAdapter<Movie>(context, 0, movies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        }

        val movie = movies[position]

        val tvTitle = itemView!!.findViewById<TextView>(R.id.tvMovieTitle)
        val tvReleaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        val tvRating = itemView.findViewById<TextView>(R.id.tvRating)
        val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        tvTitle.text = "${position + 1}. ${movie.title}"
        tvReleaseDate.text = "Release Date: ${movie.releaseDate}"
        tvRating.text = "Rating: ${movie.voteAverage}/10 (${movie.voteCount} votes)"
        tvOverview.text = movie.overview

        return itemView
    }
}

