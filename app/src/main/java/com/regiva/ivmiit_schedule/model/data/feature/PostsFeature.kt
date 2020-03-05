package com.regiva.ivmiit_schedule.model.data.feature

import android.util.Log
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.regiva.ivmiit_schedule.entity.newsfeed.PostModel
import com.regiva.ivmiit_schedule.entity.newsfeed.PostSourceModel
import com.regiva.ivmiit_schedule.entity.responses.newsfeed.ProfileModel
import com.regiva.ivmiit_schedule.model.data.feature.PostsFeature.*
import com.regiva.ivmiit_schedule.model.interactors.PostsInteractor
import io.reactivex.Observable
import javax.inject.Inject

class PostsFeature @Inject constructor(
    postsInteractor: PostsInteractor
) : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(),
    actor = ActorImpl(
        postsInteractor
    ),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val isLoading: Boolean = false,
        val posts: List<PostModel> = listOf(),
        val profiles: List<ProfileModel>? = null,
        val start_from: String? = null
    )

    sealed class Wish {
        object GetAllPosts : Wish()
    }

    sealed class Effect {
        object GetAllPostsStart : Effect()
        data class GetAllPostsSuccess(val posts: List<PostModel>, val start_from: String) : Effect()
        data class GetAllPostsFailure(val throwable: Throwable) : Effect()
    }

    sealed class News {
        data class GetAllPostsFailure(val throwable: Throwable) : News()
    }

    class ActorImpl(
        private val postsInteractor: PostsInteractor
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            is Wish.GetAllPosts ->
                postsInteractor.getNewsfeed(state.start_from)
                    .map {
                        val filteredPosts = it.response.items
                            .filter { !it.text.isNullOrBlank() && !it.attachments.isNullOrEmpty() }
                            .map { postResponse ->
                                val source = it.response.groups.find { it.id == -postResponse.owner_id}
                                val post = PostModel(
                                    source = PostSourceModel(
                                        source?.id ?: 0,
                                        source?.name ?: "name",
                                        source?.photo_100 ?: ""
                                    ),
                                    date = postResponse.date,
                                    text = postResponse.text,
                                    attachments = postResponse.attachments,
                                    post_id = postResponse.id
                                )
                                post
                            }
                        Effect.GetAllPostsSuccess(filteredPosts, it.response.next_from ?: "") as Effect
                    }
                    .startWith(Effect.GetAllPostsStart)
                    .onErrorReturn { Effect.GetAllPostsFailure(it) }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.GetAllPostsStart -> state.copy(isLoading = true)
            is Effect.GetAllPostsSuccess -> state.copy(isLoading = false, posts = state.posts + effect.posts, start_from = effect.start_from)
            is Effect.GetAllPostsFailure -> state.copy(isLoading = false)
        }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? = when (effect) {
            is Effect.GetAllPostsFailure -> News.GetAllPostsFailure(effect.throwable)
            else -> null
        }
    }
}