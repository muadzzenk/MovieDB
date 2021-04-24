package com.example.assesmentmitra.ui.lisfilm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.assesmentmitra.BuildConfig
import com.example.assesmentmitra.R
import com.example.assesmentmitra.datamodel.CastModel
import kotlinx.android.synthetic.main.layout_list_cast.view.*
import kotlinx.android.synthetic.main.layout_list_film.view.*
import kotlinx.android.synthetic.main.layout_list_film.view.ivImage
import java.util.*
import kotlin.collections.ArrayList

class CastAdapter(private val list: ArrayList<CastModel>):
        RecyclerView.Adapter<CastAdapter.ItemViewHolder>() {

    private lateinit var context: Context
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_cast, parent, false)
        context = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cast = list[position]
        showImageFromUrlWithGlide(BuildConfig.BASE_URL_IMAGE+cast.profile_path, holder.ivImage)
        holder.txtName.text = cast.name
        holder.txtCharacter.text = cast.character
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
        internal var txtName = itemView.txtName
        internal var txtCharacter = itemView.txtCharacter
    }

}
