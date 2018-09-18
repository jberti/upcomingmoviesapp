package com.example.jbert.upcomingmoviesapp

//Since genre data doesnt appear to change frequently, i decided to make a hash list mapping the names and ids.
// So sisnce this a MVP version of this app i decided to make it simple and reduce network overhead
object Genres{
    var genresMap = hashMapOf<Int, String>()

    init {
        genresMap = hashMapOf<Int, String>()
        genresMap.put(28,"Action")
        genresMap.put(12,"Adventure")
        genresMap.put(16,"Animation")
        genresMap.put(35,"Comedy")
        genresMap.put(80,"Crime")
        genresMap.put(99,"Documentary")
        genresMap.put(18,"Drama")
        genresMap.put(10751,"Family")
        genresMap.put(14,"Fantasy")
        genresMap.put(36,"History")
        genresMap.put(27,"Horror")
        genresMap.put(10402,"Music")
        genresMap.put(9648,"Mystery")
        genresMap.put(10749,"Romance")
        genresMap.put(878,"Science Fiction")
        genresMap.put(10770,"TV Movie")
        genresMap.put(53,"Thriller")
        genresMap.put(10752,"War")
        genresMap.put(37,"Western")


    }

    fun GetGenre(id: Int): String{
        return genresMap.getValue(id)
    }
}