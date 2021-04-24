package com.example.assesmentmitra.ui.home

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
import com.project.moviedb.datamodel.FilmModel
import kotlinx.android.synthetic.main.layout_list_genre_film.view.*

class FilmByGenreAdapter(private val list: MutableList<FilmModel>):
        RecyclerView.Adapter<FilmByGenreAdapter.ItemViewHolder>() {

    private lateinit var context: Context
    private var listener: FilmByGenreAdapterInterface? = null

    fun setInterface(callback: FilmByGenreAdapterInterface) {
        listener = callback
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_genre_film, parent, false)
        context = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
        showImageFromUrlWithGlide(BuildConfig.BASE_URL_IMAGE+data.poster_path, holder.ivImage)
        holder.ivImage.setOnClickListener {
            listener!!.onClickFilm(data)
        }
    }

    private fun showImageFromUrlWithGlide(link: String, imageView: ImageView) {
        val option = RequestOptions()
                .fitCenter()
                .error(R.drawable.ic_launcher_background)

        Glide.with(context)
                .load(link)
                .apply(option)
                .into(imageView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivImage = itemView.ivImage
        internal var layMain = itemView.layMain
    }

    interface FilmByGenreAdapterInterface {
        fun onClickFilm(data: FilmModel)
    }

}
