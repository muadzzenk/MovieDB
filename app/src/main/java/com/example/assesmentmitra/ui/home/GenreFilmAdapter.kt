package com.project.moviedb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.assesmentmitra.BuildConfig
import com.example.assesmentmitra.R
import com.example.assesmentmitra.datamodel.GenreFilmModel
import com.example.assesmentmitra.ui.home.BannerAdapter
import com.example.assesmentmitra.ui.home.FilmByGenreAdapter
import com.project.moviedb.datamodel.FilmModel
import com.project.moviedb.datamodel.GenreModel
import kotlinx.android.synthetic.main.layout_banner.view.*
import kotlinx.android.synthetic.main.layout_genre.view.*

class GenreFilmAdapter(private val list: MutableList<GenreFilmModel>):
        RecyclerView.Adapter<GenreFilmAdapter.ItemViewHolder>() {

    private lateinit var context: Context
    private var listener: GenreAdapterInterface? = null

    fun setInterface(callback: GenreAdapterInterface) {
        listener = callback
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_genre, parent, false)
        context = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
        holder.txtGenre.text = data.genre!!.name
        val adapter = FilmByGenreAdapter(data.film)
        adapter.setInterface(object: FilmByGenreAdapter.FilmByGenreAdapterInterface{
            override fun onClickFilm(data: FilmModel) {
                listener!!.onClickFilm(data)
            }

        })
        holder.recList.adapter = adapter
        holder.viewAll.setOnClickListener {
            listener!!.onClickViewAll(data.genre)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtGenre = itemView.txtGenre
        internal var recList = itemView.recList
        internal var viewAll = itemView.lblViewAll
    }

    interface GenreAdapterInterface {
        fun onClickViewAll(data: GenreModel?)
        fun onClickFilm(data: FilmModel)
    }

}
