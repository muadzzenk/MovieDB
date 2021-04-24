package com.project.moviedb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assesmentmitra.base.BaseViewModel
import com.example.assesmentmitra.datamodel.CastModel
import com.example.assesmentmitra.datamodel.GenreFilmModel
import com.example.assesmentmitra.datamodel.TrailerModel
import com.example.assesmentmitra.datamodel.UserReviewModel
import com.example.assesmentmitra.network.repository.film.*
import com.example.assesmentmitra.utils.Constant
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import com.project.moviedb.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmViewModel(
    private val filmRepository: FilmRepository
) : BaseViewModel() {
    var genresFilmData = SingleLiveEvent<ArrayList<FilmModel>>()
    var filmDetailData = MutableLiveData<FilmDetailResponse>()
    var castFilmData = SingleLiveEvent<ArrayList<CastModel>>()
    var trailerFilmData = SingleLiveEvent<ArrayList<TrailerModel>>()
    var userReviewFilmData = SingleLiveEvent<ArrayList<UserReviewModel>>()

    fun getGenreFilmData(req: FilmRequest) {
        viewModelScope.launch {
            try {
                showRefreshLoadingEvent.value = true
                var response: FilmResponse? = null
                withContext(Dispatchers.IO) {
                    response = filmRepository.getFilm(
                        req
                    )
                }
                hideRefreshLoadingEvent.value = true
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    genresFilmData.value = response!!.results
                } else {
                    errorEvent.value = response!!.status_message
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDetailFilm(req: FilmRequest, movie_id:Int) {
        viewModelScope.launch {
            try {
                var response: FilmDetailResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getDetailFilm(
                        req, movie_id
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    filmDetailData.value = response!!
                } else {
                    errorEvent.value = response!!.status_message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getCastFilm(req: CastRequest, movie_id:Int) {
        viewModelScope.launch {
            try {
                var response: CastResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getCast(
                        movie_id, req
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    castFilmData.value = response!!.cast
                } else {
                    errorEvent.value = response!!.status_message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTrailerFilm(req: TrailerRequest, movie_id:Int) {
        viewModelScope.launch {
            try {
                var response: TrailerResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getTrailer(
                        movie_id, req
                    )
                }
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    trailerFilmData.value = response!!.results
                } else {
                    errorEvent.value = response!!.status_message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getUserReview(req: UserReviewRequest, movie_id:Int) {
        viewModelScope.launch {
            try {
                showRefreshLoadingEvent.value = true
                var response: UserReviewResponse? = null

                withContext(Dispatchers.IO) {
                    response = filmRepository.getUserReview(
                        movie_id, req
                    )
                }
                hideRefreshLoadingEvent.value = true
                if (response!!.status_code == Constant.RESPONSE_SUCCESS) {
                    userReviewFilmData.value = response!!.results
                } else {
                    errorEvent.value = response!!.status_message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}