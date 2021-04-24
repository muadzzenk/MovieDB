package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.project.moviedb.datamodel.FilmModel

class FilmResponse : BaseResponse() {
    val results: ArrayList<FilmModel> = ArrayList()
}