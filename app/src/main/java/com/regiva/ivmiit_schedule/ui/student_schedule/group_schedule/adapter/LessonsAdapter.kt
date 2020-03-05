package com.regiva.ivmiit_schedule.ui.student_schedule.group_schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.entity.LessonModel
import com.regiva.ivmiit_schedule.util.applyDiff

class LessonsAdapter(
    private var list: List<LessonModel>
) : RecyclerView.Adapter<LessonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LessonHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_lesson,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LessonHolder, position: Int) = holder.bind(list[position])

    fun updateList(newList: List<LessonModel>) {
        applyDiff(
            oldList = list,
            newList = newList,
            areItemsTheSame = { old, new -> old.id == new.id },
            areContentsTheSame = { old, new -> old == new }
        )
        this.list = newList
    }

}