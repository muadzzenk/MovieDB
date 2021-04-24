package com.example.assesmentmitra.ui.home

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assesmentmitra.R
import com.example.assesmentmitra.base.BaseActivity
import com.example.assesmentmitra.datamodel.GenreFilmModel
import com.example.assesmentmitra.network.repository.film.FilmRequest
import com.example.assesmentmitra.network.repository.film.GenreRequest
import com.example.assesmentmitra.ui.lisfilm.FilmActivity
import com.example.assesmentmitra.ui.lisfilm.FilmDetailActivity
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import com.project.moviedb.ui.home.GenreFilmAdapter
import com.project.moviedb.ui.home.HomeViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity(), GenreFilmAdapter.GenreAdapterInterface {
    val vm: HomeViewModel by inject()
    var adapterGenre: GenreFilmAdapter? = null
    var dataGenreFilm: ArrayList<GenreFilmModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRequest()
        initObserve()
    }

    private fun initRequest() {
        requestBanner()
        requestGenre()
    }

    private fun requestBanner() {
        val req = FilmRequest()
        req.setLanguage("en-US")
        req.setSortBy("popularity.desc")
        req.setIncludeAdult(false)
        req.setIncludeVideo(false)
        req.setPage(1)
        vm.getFilmData(req)
    }

    private fun requestGenre() {
        val req = GenreRequest()
        vm.getGenres(req)
    }

    private fun requestGenreFilm(genre: GenreModel) {
        val req = FilmRequest()
        req.setLanguage("en-US")
        req.setIncludeAdult(false)
        req.setIncludeVideo(false)
        req.setPage(1)
        req.setGenre(genre.id.toString())
        vm.getGenreFilmData(req, genre)
    }

    private fun initObserve() {
        vm.bannerData.observe(this, Observer {
            setBanner(it.let {
                val banner = ArrayList<FilmModel>()
                for (i in 1..5)
                {
                    banner.add(it.get(i-1))
                }
                banner
            })
        })

        vm.genresData.observe(this, Observer {
            processGenre(it)
        })
        vm.genresFilmData.observe(this, Observer {
            setGenre(it)
        })
    }

    private fun setBanner(banner: ArrayList<FilmModel>) {
        val adapter = BannerAdapter(banner)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        imageSlider.setScrollTimeInSec(4) //set scroll delay in seconds :
        imageSlider.startAutoCycle()
        adapter.setInterface(object: BannerAdapter.BannerAdapterInterface{
            override fun onClickFilm(data: FilmModel) {
                goToDetail(data.id)
            }

        })
    }

    private fun processGenre(data: ArrayList<GenreModel>) {
        adapterGenre = GenreFilmAdapter(dataGenreFilm)
        adapterGenre!!.setInterface(this)
        recList.adapter = adapterGenre
        for (i in 1..data.size){
            requestGenreFilm(data.get(i-1))
        }
    }

    private fun setGenre(data: GenreFilmModel){
        dataGenreFilm.add(data)
        adapterGenre!!.notifyDataSetChanged()
    }

    override fun onClickViewAll(data: GenreModel?) {
        val i = Intent(this, FilmActivity::class.java)
        i.putExtra(GENRE_EXTRAS, data)
        startActivity(i)
    }

    override fun onClickFilm(data: FilmModel) {
        goToDetail(data.id)
    }

    private fun goToDetail(id: Int) {
        val i = Intent(this, FilmDetailActivity::class.java)
        i.putExtra(FilmDetailActivity.ID_FILM, id)
        startActivity(i)
    }

    companion object{
        val GENRE_EXTRAS = "GENRE"
    }

}