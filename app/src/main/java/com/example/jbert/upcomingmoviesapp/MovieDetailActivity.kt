package com.example.jbert.upcomingmoviesapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_moviedetails.*
import kotlinx.android.synthetic.main.row_item.*


class MovieDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?   ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetails)


        val intent = intent
        val movie = intent.getParcelableExtra<Movie>("movieObj")

        mapMovieToView(movie)
    }

    private fun mapMovieToView(_movie: Movie){

        fun loadImages(){
            var imageURL = AccessParameters.getImagesURL().plus(_movie.backdrop_path)
            Picasso.get().load(imageURL).into(imageView_detail_background)

            imageURL = AccessParameters.getImagesURL().plus(_movie.poster_path)
            Picasso.get().load(imageURL).into(imageView_detail_poster)
        }

        fun loadGenres() {
            var genresList = ArrayList<String>()
            _movie.genre_ids.forEach {
                val genre = Genres.GetGenre(it)
                genresList.add(genre)
            }
            textView_genres.text = "Genres: ${genresList.joinToString()}"
        }

        fun loadRating(){
            textView_detailRating.text = _movie.vote_average.toString()
            when (_movie.vote_average){
                in(0..3)     ->  textView_detailRating.setTextColor(Color.rgb(255,0,0))
                in(3.1..6.9) ->  textView_detailRating.setTextColor(Color.rgb(255, 204, 0))
                in(7..10)    ->  textView_detailRating.setTextColor(Color.rgb(0, 204, 0))
            }
        }

        loadImages()
        loadGenres()
        loadRating()

        textView_detailTitle.text = _movie.title
        textView_detailOriginalTitle.text = "Original Title: ${_movie.original_title}"
        textView_detailDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD;
        textView_detailDescription.text = _movie.overview
        textView_detailReleaseDate.text = "Release Date: ${_movie.release_date}"


    }

}