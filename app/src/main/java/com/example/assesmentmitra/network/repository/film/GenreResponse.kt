package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel

class GenreResponse : BaseResponse() {
    val genres: ArrayList<GenreModel> = ArrayList()
}