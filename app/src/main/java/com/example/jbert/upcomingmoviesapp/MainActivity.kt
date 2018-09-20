package com.example.jbert.upcomingmoviesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var maxPages = 0
    var page = 1


    lateinit var movList: MoviesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        button_previous.isEnabled = false

        
        getMoviesJSON(page)

        //made buttons to navigate because I couldn`t implement a left and right swipe Event or infinite scroll
        button_next.setOnClickListener {
            page++
            getMoviesJSON(_page = page)
            button_next.isEnabled = (page < maxPages)
            button_previous.isEnabled = (page > 1)
        }

        button_previous.setOnClickListener {
            page--
            getMoviesJSON(_page = page)
            button_previous.isEnabled = (page > 1)
            button_next.isEnabled = (page < maxPages)
        }

        recyclerView_main.addOnScrollListener(object: RecyclerView.OnScrollListener() {})

    }

    fun setCurrentPageText(){
        textView_page.text = "$page / $maxPages"
    }

    // I dont like making API calls here but i couldnt make It work when It was in a controller class.
    // Even trying making a synchronous call didnt work.
    fun getMoviesJSON(_page: Int = 1){

        val url = AccessParameters.getUpcomingMoviesURL().plus( "&page=$_page")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                TODO()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                movList = gson.fromJson(body, MoviesList::class.java)
                maxPages = movList.total_pages

                runOnUiThread {
                    recyclerView_main.adapter  = MainAdapter(movList)
                    setCurrentPageText()
                }
            }

        })

    }

}



