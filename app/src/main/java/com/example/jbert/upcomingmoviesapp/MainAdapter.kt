package com.example.jbert.upcomingmoviesapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*

class MainAdapter(val _moviesList: MoviesList): RecyclerView.Adapter<CustomViewHolder>(){
    var page = 0

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val movie = _moviesList.results[position]
        holder?._movie = movie

        holder.itemView.textView_title.text = movie.title
        holder.itemView.textView_releaseDate.text = "Release Date: "+movie.release_date

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

class CustomViewHolder(val view: View, var _movie: Movie? = null): RecyclerView.ViewHolder(view) {

    fun getGenresString(): String {
        var genresList = ArrayList<String>()

        _movie?.genre_ids?.forEach {
            val genre = Genres.GetGenre(it)
            genresList.add(genre)
        }
        return  genresList.joinToString()

    }

    init{
        view.setOnClickListener {

            val intent = Intent(view.context,MovieDetailActivity::class.java)

            //the initial idea was to pass the whoel movie object to the other activitie, but I was geting
            // a lot of null exceptions, when getting the object,in cases where the movie didnt have a poster url
            intent.putExtra("title",_movie?.title.toString())
            intent.putExtra("originalTitle",_movie?.original_title.toString())
            intent.putExtra("overview",_movie?.overview.toString())
            intent.putExtra("releaseDate",_movie?.release_date.toString())
            intent.putExtra("voteAverage",_movie?.vote_average)
            intent.putExtra("genres",getGenresString())
            intent.putExtra("posterPath",_movie?.poster_path.toString())
            intent.putExtra("backgroundPath",_movie?.backdrop_path.toString())
            view.context.startActivity(intent)
        }

    }
}