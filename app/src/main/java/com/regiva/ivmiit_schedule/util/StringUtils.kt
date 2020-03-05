package com.regiva.ivmiit_schedule.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertToDateFormat(
    dateFormat: String = "dd MMMM yyyy"
): String = SimpleDateFormat(dateFormat, Locale.getDefault()).format(Date(this.times(1000)))