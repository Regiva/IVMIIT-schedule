package com.regiva.ivmiit_schedule.ui.student_schedule.group_schedule.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.regiva.ivmiit_schedule.entity.LessonModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_lesson.*

class LessonHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(
        data: LessonModel
    ) {
        tv_title.text = data.title
        tv_cabinet.text = data.cabinet
        tv_type.text = data.type
        tv_teacher.text = data.teacher
        tv_start.text = when(data.serial.substringBeforeLast(".", "-").toInt()) {
            1 -> "8:30"
            2 -> "10:10"
            3 -> "11:50"
            4 -> "14:00"
            5 -> "15:40"
            6 -> "17:50"
            7 -> "19:30"
            else -> "-"
        }
    }

}