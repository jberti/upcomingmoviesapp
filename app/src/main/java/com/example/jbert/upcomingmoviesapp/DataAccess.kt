package com.example.jbert.upcomingmoviesapp

//made It static but the inital idea is to make an interface or abstract class to make possible different ways to retrieve de API data access
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
