package com.example.jbert.upcomingmoviesapp

import android.widget.TextView
import okhttp3.*
import java.io.IOException

object AccessParameters{

    fun getAPIKey(): String{
        return "1f54bd990f1cdfb230adb312546d765d"
    }

    fun getUpcomingMoviesURL():String{
        return "https://api.themoviedb.org/3/movie/upcoming?api_key=${getAPIKey()}"
    }

    fun getImagesURL(): String{
        return "https://image.tmdb.org/t/p/w500/"
    }
}
