package com.example.assesmentmitra.ui.home

import android.annotation.SuppressLint
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
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.layout_banner.view.*

class BannerAdapter(private val list: MutableList<FilmModel>):
    SliderViewAdapter<BannerAdapter.ResiItemViewHolder>() {

    private lateinit var context: Context
    private var listener: BannerAdapterInterface? = null

    fun setInterface(callback: BannerAdapterInterface) {
        listener = callback
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ResiItemViewHolder {
        context = parent!!.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_banner, parent, false)
        return ResiItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ResiItemViewHolder, position: Int) {
        showImageFromUrlWithGlide(BuildConfig.BASE_URL_IMAGE+list[position].poster_path, holder.ivBanner)
        holder.ivBanner.setOnClickListener {
            listener!!.onClickFilm(list[position])
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

    inner class ResiItemViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        internal var ivBanner = itemView.banner

    }

    interface BannerAdapterInterface {
        fun onClickFilm(data: FilmModel)
    }

}
