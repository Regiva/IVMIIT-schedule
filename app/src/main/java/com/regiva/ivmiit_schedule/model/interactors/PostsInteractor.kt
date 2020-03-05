package com.regiva.ivmiit_schedule.model.interactors

import com.regiva.ivmiit_schedule.model.repositories.PostsRepository
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository
) {
    //todo
    fun getNewsfeed(start_from: String?) = postsRepository.getNewsfeed(start_from)

}