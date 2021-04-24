package com.example.assesmentmitra.ui.lisfilm

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmentmitra.R
import com.example.assesmentmitra.base.BaseActivity
import com.example.assesmentmitra.datamodel.GenreFilmModel
import com.example.assesmentmitra.network.repository.film.FilmRequest
import com.example.assesmentmitra.network.repository.film.GenreRequest
import com.example.assesmentmitra.ui.home.HomeActivity
import com.example.assesmentmitra.utils.EndlessViewScrollListener
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import com.project.moviedb.ui.home.FilmViewModel
import kotlinx.android.synthetic.main.activity_list_film.*
import kotlinx.android.synthetic.main.toolbar_main_layout.*
import org.koin.android.ext.android.inject

class FilmActivity : BaseActivity() {
    val vm: FilmViewModel by inject()
    private lateinit var scrollListener: EndlessViewScrollListener
    var genre: GenreModel? = null
    var dataFilm: ArrayList<FilmModel> = ArrayList()
    var adapter: FilmAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_film)
        initData()
        initToolbar()
        initView()
        initListener()
        initRequest()
        initObserve()
    }

    private fun initData() {
        genre = intent.extras!!.getSerializable(HomeActivity.GENRE_EXTRAS) as GenreModel

    }
    private fun initToolbar() {
        tbMain.setNavigationOnClickListener { onBackPressed() }
        textViewTitleAppBar.text = "Genre ${genre!!.name}"
    }

    private fun initView(){
        adapter = FilmAdapter(dataFilm)
        recList.adapter = adapter
        adapter!!.setInterface(object: FilmAdapter.FilmAdapterInterface{
            override fun onClickReview(data: FilmModel) {
                goToUserReview(data.id)
            }

            override fun onClickDetail(data: FilmModel) {
                goToDetail(data.id)
            }

        })
    }

    private fun initRequest(){
        requestFilm(1)
    }

    private fun requestFilm(page: Int) {
        val req = FilmRequest()
        req.setLanguage("en-US")
        req.setIncludeAdult(false)
        req.setIncludeVideo(true)
        req.setPage(page)
        req.setGenre(genre!!.id.toString())
        vm.getGenreFilmData(req)
    }

    private fun initListener() {
        scrollListener = object : EndlessViewScrollListener(recList.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                requestFilm(page + 1)
            }
        }
        recList.addOnScrollListener(scrollListener)

        refresh.setOnRefreshListener {
            clearData()
        }
    }

    private fun initObserve() {
        vm.genresFilmData.observe(this, Observer {
            setData(it)
        })
        vm.showRefreshLoadingEvent.observe(this, Observer {
            refresh.isRefreshing = true
        })
        vm.hideRefreshLoadingEvent.observe(this, Observer {
            refresh.isRefreshing = false
        })
    }

    private fun setData(data: ArrayList<FilmModel>){
        dataFilm.addAll(data)
        checkEmpty()
    }

    private fun clearData(){
        scrollListener.resetState()
        dataFilm.clear()
        adapter!!.notifyDataSetChanged()
        requestFilm(1)
    }

    private fun checkEmpty(){
        if (dataFilm.size == 0){
            txtEmpty.visibility = View.VISIBLE
        } else{
            txtEmpty.visibility = View.GONE
        }
        adapter!!.notifyDataSetChanged()
    }

    private fun goToDetail(id: Int) {
        val i = Intent(this, FilmDetailActivity::class.java)
        i.putExtra(FilmDetailActivity.ID_FILM, id)
        startActivity(i)
    }

    private fun goToUserReview(id: Int) {
        val i = Intent(this, UserReviewActivity::class.java)
        i.putExtra(UserReviewActivity.ID_FILM, id)
        startActivity(i)
    }

}