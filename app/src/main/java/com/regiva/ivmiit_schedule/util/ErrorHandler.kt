package com.regiva.ivmiit_schedule.util

import android.content.Context
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.model.data.error.ServerError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val context: Context
) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        error.printStackTrace()
        messageListener(
            when (error) {
                is ServerError -> error.localizedMessage
                is HttpException -> context.getString(R.string.server_error)
                is IOException -> context.getString(R.string.connection_error)
                else -> context.getString(R.string.unknown_error)
            }
        )
    }

}