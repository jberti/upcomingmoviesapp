package com.example.jbert.upcomingmoviesapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_moviedetails.*
import java.time.LocalDate
import java.time.LocalDate.parse


class MovieDetailActivity: AppCompatActivity() {

    lateinit var title: String
    lateinit var originalTitle: String
    lateinit var overView: String
    lateinit var releaseDate: String
    var voteAverage: Float = 0F
    lateinit var genres: String
    lateinit var posterPath: String
    lateinit var backgroundPath: String



    override fun onCreate(savedInstanceState: Bundle?   ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviedetails)


        val intent = intent

        title = intent.getStringExtra("title")
        originalTitle = intent.getStringExtra("originalTitle")
        overView = intent.getStringExtra("overview")
        releaseDate =intent.getStringExtra("releaseDate")
        voteAverage = intent.getDoubleExtra("voteAverage", 0.0).toFloat()
        genres =intent.getStringExtra("genres")
        posterPath =intent.getStringExtra("posterPath")
        backgroundPath =intent.getStringExtra("backgroundPath")

        mapMovieToView()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapMovieToView(){

        val releaseDate = parse(releaseDate)
        val now = LocalDate.now()
        val toBeReleased = (releaseDate > now)

        fun loadImages(){
            var imageURL: String

            if (backgroundPath != "null") {
                imageURL = AccessParameters.getImagesURL().plus(backgroundPath)
                Picasso.get().load(imageURL).into(imageView_detail_background)
            }

            if (posterPath != "null") {
                imageURL = AccessParameters.getImagesURL().plus(posterPath)
                Picasso.get().load(imageURL).into(imageView_detail_poster)
            }
        }

        fun loadRating(){

            if (toBeReleased) {
                textView_detailRating.text = "NA"
                }
                else{
                    textView_detailRating.text = voteAverage.toString()
                    when (voteAverage) {
                        in (0..3) -> textView_detailRating.setTextColor(Color.rgb(255, 0, 0))
                        in (3.1..6.9) -> textView_detailRating.setTextColor(Color.rgb(255, 204, 0))
                        in (7..10) -> textView_detailRating.setTextColor(Color.rgb(0, 204, 0))
                }
            }
        }

        loadImages()
        loadRating()

        textView_detailTitle.text = title
        textView_genres.text = "Genres:  $genres"
        textView_detailOriginalTitle.text = "Original Title: $originalTitle"
        textView_detailDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD;
        textView_detailDescription.text = overView
        textView_detailReleaseDate.text = "Release Date: $releaseDate"


    }

}