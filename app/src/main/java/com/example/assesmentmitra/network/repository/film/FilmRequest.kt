package com.example.assesmentmitra.network.repository.film

import com.example.assesmentmitra.base.BaseRequest

class FilmRequest : BaseRequest() {

    fun setLanguage (language: String) {
        put("language", language)
    }
    fun setSortBy (sort_by: String) {
        put("sort_by", sort_by)
    }
    fun setIncludeAdult (include_adult: Boolean) {
        put("include_adult", include_adult)
    }
    fun setIncludeVideo (include_video: Boolean) {
        put("include_video", include_video)
    }
    fun setPage (page: Int) {
        put("page", page)
    }
    fun setYear (year: Int) {
        put("year", year)
    }
    fun setGenre (genre: String) {
        put("with_genres", genre)
    }
}
