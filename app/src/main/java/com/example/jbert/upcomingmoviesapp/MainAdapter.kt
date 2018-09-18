package com.example.jbert.upcomingmoviesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*

class MainAdapter(val _moviesList: MoviesList): RecyclerView.Adapter<CustomViewHolder>(){
    var page = 0

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.textView_title.text = _moviesList.results[position].title
        holder.itemView.textView_releaseDate.text = _moviesList.results[position].release_date

        val backgroundView = holder.itemView.imageView_BackGround
        val imageURL = AccessParameters.getImagesURL().plus(_moviesList.results[position].backdrop_path)
        Picasso.get().load(imageURL).into(backgroundView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val row = layoutInflater.inflate(R.layout.row_item, parent, false)
        return CustomViewHolder(row)
    }

    override fun getItemCount(): Int {
        return _moviesList.results.count()
    }
}

class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

}