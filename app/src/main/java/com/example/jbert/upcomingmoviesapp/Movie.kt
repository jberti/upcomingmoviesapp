package com.example.jbert.upcomingmoviesapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//the model representing the JSON returned by the API service. So much easy to parse than when i did when programming with Delphi 2010.
class MoviesList(val total_pages: Int, val results: List<Movie>)

@Parcelize
data class Movie(
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        val backdrop_path: String?,
        val adult: Boolean,
        val overview: String,
        val release_date: String
): Parcelable