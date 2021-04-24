package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseRequest

class UserReviewRequest: BaseRequest() {
    fun setLanguage (language: String) {
        put("language", language)
    }
    fun setPage(page: Int) {
        put("page", page)
    }
}