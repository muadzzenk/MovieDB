package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseResponse
import com.example.assesmentmitra.datamodel.CastModel

class CastResponse : BaseResponse() {
    val cast: ArrayList<CastModel> = ArrayList()
    val id: Int = 0
}