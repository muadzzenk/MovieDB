package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.example.assesmentmitra.datamodel.TrailerModel

class TrailerResponse : BaseResponse() {
    val results: ArrayList<TrailerModel> = ArrayList()
    val id: Int = 0
}