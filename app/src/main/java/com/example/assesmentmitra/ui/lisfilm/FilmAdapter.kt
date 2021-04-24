package com.example.assesmentmitra.ui.lisfilm

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
import com.example.assesmentmitra.ui.home.BannerAdapter
import com.project.moviedb.datamodel.FilmModel
import kotlinx.android.synthetic.main.layout_list_film.view.*

class FilmAdapter(private val list: MutableList<FilmModel>):
        RecyclerView.Adapter<FilmAdapter.ItemViewHolder>() {

    private lateinit var context: Context
    private var listener: FilmAdapterInterface? = null

    fun setInterface(callback: FilmAdapterInterface) {
        listener = callback
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_film, parent, false)
        context = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
        showImageFromUrlWithGlide(BuildConfig.BASE_URL_IMAGE+data.poster_path, holder.ivImage)
        holder.txtTitle.text = data.title
        holder.txtDate.text = data.release_date
        holder.btnReview.setOnClickListener {
            listener!!.onClickReview(data)
        }
        holder.cardImage.setOnClickListener {
            listener!!.onClickDetail(data)
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
        internal var txtDate = itemView.txtDate
        internal var txtTitle = itemView.txtTitle
        internal var btnReview = itemView.txtReview
        internal var cardImage = itemView.cardBanner
    }

    interface FilmAdapterInterface {
        fun onClickReview(data: FilmModel)
        fun onClickDetail(data: FilmModel)
    }
}
