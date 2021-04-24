package com.example.assesmentmitra.di

import com.example.assesmentmitra.network.repository.film.FilmRepository
import com.example.assesmentmitra.network.repository.film.FilmService
import com.project.moviedb.ui.home.FilmViewModel
import com.project.moviedb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {

//    factory { BannerRepository(get<Retrofit>(named(RESTAPI_SERVICE)).create(BannerService::class.java)) }
    factory { FilmRepository(get<Retrofit>(named(RESTAPI_SERVICE)).create(FilmService::class.java)) }
//    factory { FavoriteRepository(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { FilmViewModel(get()) }
//    viewModel { FavoriteViewModel(get()) }
}