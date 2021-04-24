package com.project.moviedb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assesmentmitra.base.BaseViewModel
import com.example.assesmentmitra.datamodel.GenreFilmModel
import com.example.assesmentmitra.network.repository.film.*
import com.example.assesmentmitra.utils.Constant
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import com.project.moviedb.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val filmRepository: FilmRepository
) : BaseViewModel() {

    var bannerData = MutableLiveData<ArrayList<FilmModel>>()
    var genresData = MutableLiveData<ArrayList<GenreModel>>()
    var genresFilmData = SingleLiveEvent<GenreFilmModel>()

    fun getFilmData(req: FilmRequest) {
        viewModelScope.launch {
            try {
                var response: FilmResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getFilm(
                        req
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    bannerData.value = response?.results
                } else {
                    errorEvent.value = response!!.status_message
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getGenreFilmData(req: FilmRequest, genreValue: GenreModel) {
        viewModelScope.launch {
            try {
                var response: FilmResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getFilm(
                        req
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    val data = GenreFilmModel()
                    data.film = response!!.results
                    data.genre = genreValue
                    genresFilmData.value = data
                } else {
                    errorEvent.value = response!!.status_message
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getGenres(req: GenreRequest) {
        viewModelScope.launch {
            try {
                var response: GenreResponse? = null

                withContext(Dispatchers.IO) {

                    response = filmRepository.getGenreFilm(
                        req
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    genresData.value = response!!.genres
                } else {
                    errorEvent.value = response!!.status_message
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}