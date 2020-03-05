package com.regiva.ivmiit_schedule.entity.newsfeed

import android.os.Parcelable
import com.regiva.ivmiit_schedule.entity.responses.newsfeed.Attachment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PostModel(
    val source: @RawValue PostSourceModel,
    val date: Long,
    val text: String?,
    val attachments: @RawValue List<Attachment>?,
    val post_id: Long,
    var isLiked: Boolean = false
) : Parcelable

data class PostSourceModel(
    val id: Long,
    val name: String,
    val photo: String
)