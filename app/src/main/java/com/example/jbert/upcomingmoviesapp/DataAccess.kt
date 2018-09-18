package com.example.jbert.upcomingmoviesapp

import okhttp3.*
import java.io.IOException

private class accessParameters(){

    fun getAPIKey(): String{
        return "1f54bd990f1cdfb230adb312546d765d"
    }

    fun getUpcomingMoviesURL():String{
        return "https://api.themoviedb.org/3/movie/upcoming?api_key=${getAPIKey()}"
    }
}

class MoviesDataRequester(){

    private val accessParameters = accessParameters()

    fun getMoviesJSON(_page: Int = 1): String{
        val url = accessParameters.getUpcomingMoviesURL().plus( "{'$'}page=$_page")

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        var responseBody = " "

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                responseBody = e.toString()
            }

            override fun onResponse(call: Call?, response: Response?) {
                responseBody = response?.body()?.string()!!
            }

        })
        return responseBody
    }
}