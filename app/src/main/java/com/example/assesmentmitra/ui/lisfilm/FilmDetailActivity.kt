package com.example.assesmentmitra.ui.lisfilm

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.assesmentmitra.BuildConfig
import com.example.assesmentmitra.R
import com.example.assesmentmitra.base.BaseActivity
import com.example.assesmentmitra.datamodel.CastModel
import com.example.assesmentmitra.datamodel.TrailerModel
import com.example.assesmentmitra.network.repository.film.CastRequest
import com.example.assesmentmitra.network.repository.film.FilmDetailResponse
import com.example.assesmentmitra.network.repository.film.FilmRequest
import com.example.assesmentmitra.network.repository.film.TrailerRequest
import com.example.assesmentmitra.utils.DateFormat
import com.project.moviedb.ui.home.FilmViewModel
import kotlinx.android.synthetic.main.activity_detail_film.*
import kotlinx.android.synthetic.main.toolbar_detail_film.*
import org.koin.android.ext.android.inject


class FilmDetailActivity : BaseActivity() {
    private val vm: FilmViewModel by inject()
    private var detail: FilmDetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        initToolbar()
        initView()
        initObserver()
        initRequest()
        initListener()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initView(){

    }

    private fun initListener () {
        btnTrailer.setOnClickListener {
            requestTrailer()
        }
        txtReview.setOnClickListener {
            val id = intent.extras!!.getInt(ID_FILM)
            goToUserReview(id)
        }
    }

    private fun initObserver() {
        vm.filmDetailData.observe(this, Observer {
            detail = it
            setData(it)
        })
        vm.castFilmData.observe(this, Observer {
            setDataCast(it)
        })
        vm.trailerFilmData.observe(this, Observer {
            btnTrailer.isFocusable = true
            showTrailer(it.get(0))
        })
    }

    private fun initRequest () {
        if (intent.hasExtra(ID_FILM)) {
            requestDetail()
            requestCast()
        }
    }

    private fun requestDetail() {
        val id = intent.extras!!.getInt(ID_FILM)
        val req = FilmRequest()
        req.setLanguage("en-US")
        req.setIncludeVideo(true)
        vm.getDetailFilm(req, id)
    }

    private fun requestCast() {
        val id = intent.extras!!.getInt(ID_FILM)
        val req = CastRequest()
        vm.getCastFilm(req, id)
    }

    private fun requestTrailer() {
        btnTrailer.isFocusable = false
        val id = intent.extras!!.getInt(ID_FILM)
        val req = TrailerRequest()
        vm.getTrailerFilm(req, id)
    }

    private fun setData(it: FilmDetailResponse) {
        showImageFromUrlWithGlide(BuildConfig.BASE_URL_IMAGE+it.poster_path, image)
        txtDesc.text = it.overview
        txtTitle.text = it.original_title
        txtDate.text = DateFormat.convertToNoZone(it.release_date, DateFormat.DATE_MONTH_YEAR_FORMAT)
        var listGenre = ""
        it.genres.forEach {
            listGenre = listGenre + it.name + " " + addColorBullet("\u2022") + " "
        }
        txtGenre.text = listGenre.dropLast(2)
        txtPopularity.text = it.popularity
    }

    private fun setDataCast(it: ArrayList<CastModel>) {
        val adapter = CastAdapter(it)
        recCast.adapter = adapter
    }

    private fun showTrailer(it: TrailerModel) {
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/watch?v=${it.key}")
        )
        try {
            startActivity(webIntent)
        } catch (ex: ActivityNotFoundException) {
            showToast(this, "error load video")
        }
    }

    private fun addColorBullet(bul: String) : String {
        val spannable = SpannableString(bul)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange)),
            0, spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable.toString()
    }

    private fun goToUserReview(id: Int) {
        val i = Intent(this, UserReviewActivity::class.java)
        i.putExtra(UserReviewActivity.ID_FILM, id)
        startActivity(i)
    }

    companion object {
        val ID_FILM = "ID_FILM"
    }

}