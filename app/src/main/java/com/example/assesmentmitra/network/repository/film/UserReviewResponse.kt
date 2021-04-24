package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.example.assesmentmitra.datamodel.UserReviewModel
import com.project.moviedb.datamodel.FilmModel

class UserReviewResponse : BaseResponse() {
    val results: ArrayList<UserReviewModel> = ArrayList()
}