package com.example.task_diagnal_technologies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task_diagnal_technologies.R
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.romantic_comedy.view.*

class RomanticComedyPaggingAdapter  : PagedListAdapter<RomanticComedy.Page.ContentItems.Content, RomanticComedyPaggingAdapter.RomanticComedyViewHolder>(RomanticComedyDiffCallback()){

    inner class RomanticComedyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image
        val text = itemView.text
    }

    class RomanticComedyDiffCallback : DiffUtil.ItemCallback<RomanticComedy.Page.ContentItems.Content>() {

        override fun areItemsTheSame(oldItem: RomanticComedy.Page.ContentItems.Content, newItem: RomanticComedy.Page.ContentItems.Content): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RomanticComedy.Page.ContentItems.Content,
            newItem: RomanticComedy.Page.ContentItems.Content
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RomanticComedyViewHolder {
        return RomanticComedyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.romantic_comedy, parent, false))

    }

    override fun onBindViewHolder(holder: RomanticComedyViewHolder, position: Int) {
        val result : RomanticComedy.Page.ContentItems.Content? = getItem(position)!!
        Picasso.get().load(Constants.baseImageUrl+result!!.posterImage).into(holder.image)
        holder.text.text = "${result!!.name}"
    }
}