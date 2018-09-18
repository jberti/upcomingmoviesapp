package com.example.jbert.upcomingmoviesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import kotlinx.android.parcel.Parcelize


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)

        getMoviesJSON(1)
    }

    fun getMoviesJSON(_page: Int = 1){

        val url = AccessParameters.getUpcomingMoviesURL().plus( "&page=$_page")

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()


        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val moviesList = gson.fromJson(body, MoviesList::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(moviesList)
                }
            }

        })

    }

}

