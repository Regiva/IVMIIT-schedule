package com.regiva.ivmiit_schedule.entity

data class LessonModel(
    val id: String,
    val title: String,
    val teacher: String,
    val cabinet: String,
    val type: String,
    val serial: String
)