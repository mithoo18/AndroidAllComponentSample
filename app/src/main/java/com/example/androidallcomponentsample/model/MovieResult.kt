package com.example.androidallcomponentsample.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("page") var page : Int = 0,
    @SerializedName("total_reult") var totalResults : Int =0,
    @SerializedName("total_pages") var totalPages : Int = 0,
    @SerializedName("results") var result: List<Result> = listOf()
)


data class Result(
    @SerializedName("vote_count") var voteCount : Int = 0,
    @SerializedName("id") var id : Int = 0,
    @SerializedName("video") var video : Boolean = false,
    @SerializedName("vote_average") var voteAverage : Double = 0.0,
    @SerializedName("title") var title : String = "",
    @SerializedName("popularity") var popularity : Double = 0.0,
    @SerializedName("poster_path") var posterPath : String ="",
    @SerializedName("original_language") var originalLanguage : String ="",
    @SerializedName("original_title") var originalTitle : String ="",
    @SerializedName("genre_ids") var genreIds : List<Int> = listOf(),
    @SerializedName("backdrop_path") var backdropPath : String ="",
    @SerializedName("adult") var adult :Boolean = false,
    @SerializedName("overview") var overview : String = "",
    @SerializedName("release_date") var releaseDate : String = ""
)