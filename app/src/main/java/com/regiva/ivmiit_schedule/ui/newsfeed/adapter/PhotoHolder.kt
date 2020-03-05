package com.regiva.ivmiit_schedule.ui.newsfeed.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.regiva.ivmiit_schedule.entity.responses.newsfeed.Attachment
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(
        position: Int,
        photo: Attachment,
        photos: List<Attachment>
    ) {
        containerView.setOnClickListener {
            StfalconImageViewer.Builder(containerView.context, photos.map { it.photo?.photo_807 ?: it.photo?.photo_604 }) { view, image ->
                Glide.with(containerView.context)
                    .load(image)
                    .into(view)
            }.show()
                .setCurrentPosition(position)
//            ImageViewer.Builder(containerView.context, photos.map { it.photo?.photo_807 ?: it.photo?.photo_604 })
//                .setStartPosition(position)
//                .show()
        }
        Glide.with(containerView.context)
            .load(photo.photo?.photo_604 ?: photo.photo?.photo_130 ?: photo.photo?.photo_807 ?: photo.photo?.photo_1280 ?: photo.photo?.photo_75)
//            .placeholder(R.drawable.ic_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(containerView.iv_photo)
    }
}