package com.example.assesmentmitra.network.repository.film

class FilmRepository(private val filmService: FilmService) {

    suspend fun getGenreFilm(req: GenreRequest): GenreResponse =
        filmService.getGenreFilm(req)

    suspend fun getFilm(req: FilmRequest): FilmResponse =
        filmService.getFilm(req)

    suspend fun getCast(movie_id: Int, req: CastRequest): CastResponse =
        filmService.getCastFilm(movie_id, req)

    suspend fun getTrailer(movie_id: Int, req: TrailerRequest): TrailerResponse =
        filmService.getTrailerFilm(movie_id, req)

    suspend fun getUserReview(movie_id: Int, req: UserReviewRequest): UserReviewResponse =
        filmService.getUserReview(movie_id, req)

    suspend fun getDetailFilm(req: FilmRequest, movie_id: Int): FilmDetailResponse =
        filmService.getFilmDetail(movie_id, req)

}