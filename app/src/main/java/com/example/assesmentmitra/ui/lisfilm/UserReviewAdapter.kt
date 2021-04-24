package com.example.assesmentmitra.ui.lisfilm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmentmitra.R
import com.example.assesmentmitra.datamodel.UserReviewModel
import kotlinx.android.synthetic.main.layout_user_review.view.*
import java.util.*
import kotlin.collections.ArrayList

class UserReviewAdapter(private val list: ArrayList<UserReviewModel>):
        RecyclerView.Adapter<UserReviewAdapter.ItemViewHolder>() {

    private lateinit var context: Context
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user_review, parent, false)
        context = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]
        holder.txtName.text = data.author
        holder.txtReview.text = data.content
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtName = itemView.txtName
        internal var txtReview = itemView.txtReview
    }

}
