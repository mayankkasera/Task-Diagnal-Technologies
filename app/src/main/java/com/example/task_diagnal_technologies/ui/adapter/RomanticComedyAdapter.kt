package com.example.task_diagnal_technologies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_diagnal_technologies.R
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.romantic_comedy.view.*

class RomanticComedyAdapter(val context: Context,var list : List<RomanticComedy.Page.ContentItems.Content>) :
    RecyclerView.Adapter<RomanticComedyAdapter.RomanticComedyViewHolder>() {

    inner class RomanticComedyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image
        val text = itemView.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RomanticComedyViewHolder {
        return RomanticComedyViewHolder(LayoutInflater.from(context).inflate(R.layout.romantic_comedy, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RomanticComedyViewHolder, position: Int) {
          Picasso.get().load(Constants.baseImageUrl+list.get(position).posterImage).into(holder.image)
            holder.text.text = list.get(position).name
    }
}