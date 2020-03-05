package com.regiva.ivmiit_schedule.model.data.network

import com.regiva.ivmiit_schedule.Constants
import com.regiva.ivmiit_schedule.entity.responses.base.BaseResponse
import com.regiva.ivmiit_schedule.entity.responses.newsfeed.GetNewsfeedResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("wall.get")
    fun getNewsfeed(
        @Query("access_token") token: String,
        @Query("v") v: String = Constants.Api.API_VERSION,
        @Query("owner_id") owner_id: String = "-114026680",
        @Query("domain") domain: String = "ivmiit",
        @Query("count") count: Int = 20,
        @Query("filter") filter: String = "owner",
        @Query("extended") extended: Int = 1,
        @Query("start_from") start_from: String?
    ): Observable<BaseResponse<GetNewsfeedResponse>>

}