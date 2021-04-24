package com.example.assesmentmitra.network.repository.film

import retrofit2.http.*

interface FilmService {
    @GET("/3/discover/movie")
    suspend fun getFilm(
        @QueryMap parameter: FilmRequest
    ): FilmResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getFilmDetail(
        @Path("movie_id") movie_id: Int,
        @QueryMap parameter: FilmRequest
    ): FilmDetailResponse

    @GET("/3/genre/movie/list")
    suspend fun getGenreFilm(
        @QueryMap parameter: GenreRequest
    ): GenreResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getCastFilm(
        @Path("movie_id") movie_id: Int,
        @QueryMap parameter: CastRequest
    ): CastResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getTrailerFilm(
        @Path("movie_id") movie_id: Int,
        @QueryMap parameter: TrailerRequest
    ): TrailerResponse

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getUserReview(
        @Path("movie_id") movie_id: Int,
        @QueryMap parameter: UserReviewRequest
    ): UserReviewResponse
}