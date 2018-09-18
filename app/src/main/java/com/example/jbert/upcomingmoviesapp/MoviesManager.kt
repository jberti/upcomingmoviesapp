package com.example.jbert.upcomingmoviesapp


import android.os.Parcelable
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize

class MoviesList(val movies: List<Video>)

class MoviesManager(){

    fun fetchMovies(_page: Int): MoviesList{
        val dataAccess = MoviesDataRequester()
        val moviesJSON = dataAccess.getMoviesJSON(_page = _page)

        val gson = GsonBuilder().create()
        return gson.fromJson(moviesJSON, MoviesList::class.java)

    }
}

@Parcelize
data class Video(val title: String, val genre_ids: List<Int>, val overview: String, val release_date: String,
                     val poster_path: String, val backdrop_path: String ) : Parcelable




