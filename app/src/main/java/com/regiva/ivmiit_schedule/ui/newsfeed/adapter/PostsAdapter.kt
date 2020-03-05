package com.regiva.ivmiit_schedule.ui.newsfeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.entity.newsfeed.PostModel
import com.regiva.ivmiit_schedule.ui.newsfeed.adapter.PostHolder
import com.regiva.ivmiit_schedule.util.applyDiff

class PostsAdapter(
    private var list: List<PostModel>
) : RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) = holder.bind(list[position])

    fun updateList(newList: List<PostModel>) {
        applyDiff(
            oldList = list,
            newList = newList,
            areItemsTheSame = { old, new -> old.post_id == new.post_id },
            areContentsTheSame = { old, new -> (old == new) && (old.isLiked == new.isLiked) }
        )
        this.list = newList
    }
}