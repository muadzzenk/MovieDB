package com.example.assesmentmitra.ui.lisfilm

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmentmitra.R
import com.example.assesmentmitra.base.BaseActivity
import com.example.assesmentmitra.datamodel.UserReviewModel
import com.example.assesmentmitra.network.repository.film.FilmRequest
import com.example.assesmentmitra.network.repository.film.UserReviewRequest
import com.example.assesmentmitra.ui.home.HomeActivity
import com.example.assesmentmitra.utils.EndlessViewScrollListener
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import com.project.moviedb.ui.home.FilmViewModel
import kotlinx.android.synthetic.main.activity_list_film.*
import kotlinx.android.synthetic.main.toolbar_main_layout.*
import org.koin.android.ext.android.inject

class UserReviewActivity : BaseActivity() {
    private val vm: FilmViewModel by inject()
    private lateinit var scrollListener: EndlessViewScrollListener
    var dataUserReview: ArrayList<UserReviewModel> = ArrayList()
    var adapter: UserReviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user_review)
        initToolbar()
        initView()
        initObserve()
        initRequest()
        initListener()
    }

    private fun initToolbar() {
        tbMain.setNavigationOnClickListener { onBackPressed() }
        textViewTitleAppBar.text = "User Review"
    }

    private fun initRequest() {
        if (intent.hasExtra(UserReviewActivity.ID_FILM)) {
            requestUserReview(1)
        }
    }

    private fun requestUserReview(page: Int){
        val movie_id = intent.extras!!.getInt(UserReviewActivity.ID_FILM)
        val req = UserReviewRequest()
        req.setLanguage("en-US")
        req.setPage(page)
        vm.getUserReview(req, movie_id)
    }

    private fun initView(){
        adapter = UserReviewAdapter(dataUserReview)
        recList.adapter = adapter
    }

    private fun initListener() {
        scrollListener = object : EndlessViewScrollListener(recList.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                requestUserReview(page + 1)
            }
        }
        recList.addOnScrollListener(scrollListener)

        refresh.setOnRefreshListener {
            clearData()
        }
    }

    private fun initObserve() {
        vm.userReviewFilmData.observe(this, Observer {
            setData(it)
        })
        vm.showRefreshLoadingEvent.observe(this, Observer {
            refresh.isRefreshing = true
        })
        vm.hideRefreshLoadingEvent.observe(this, Observer {
            refresh.isRefreshing = false
        })
    }

    private fun setData(data: ArrayList<UserReviewModel>){
        dataUserReview.addAll(data)
        checkEmpty()
    }

    private fun clearData(){
        scrollListener.resetState()
        dataUserReview.clear()
        adapter!!.notifyDataSetChanged()
        requestUserReview(1)
    }

    private fun checkEmpty(){
        if (dataUserReview.size == 0){
            txtEmpty.visibility = View.VISIBLE
        } else{
            txtEmpty.visibility = View.GONE
        }
        adapter!!.notifyDataSetChanged()
    }

    companion object {
        val ID_FILM = "ID_FILM"
    }

}