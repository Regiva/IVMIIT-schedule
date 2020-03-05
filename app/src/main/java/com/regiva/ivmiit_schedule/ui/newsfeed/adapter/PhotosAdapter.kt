package com.regiva.ivmiit_schedule.ui.newsfeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.entity.responses.newsfeed.Attachment
import com.regiva.ivmiit_schedule.ui.newsfeed.adapter.PhotoHolder

class PhotosAdapter (
    var list: List<Attachment>
) : RecyclerView.Adapter<PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) =
        holder.bind(
            position,
            list[position],
            list
        )

    fun updateList(list: List<Attachment>) {
        this.list = list
        notifyDataSetChanged()
    }
}