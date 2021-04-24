package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.project.moviedb.datamodel.GenreModel

class FilmDetailResponse : BaseResponse() {
    val backdrop_path: String = ""
    val genres: ArrayList<GenreModel> = ArrayList()
    val id: Int = 0
    val original_title: String = ""
    val title: String = ""
    val overview: String = ""
    val poster_path: String = ""
    val release_date: String = ""
    val popularity: String = ""
}