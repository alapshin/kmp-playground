package dev.bitbakery.boilerplate.post.data

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Url
import dev.bitbakery.boilerplate.BuildKonfig
import dev.bitbakery.boilerplate.network.ApiError

interface PostApi {
    @GET("posts/")
    suspend fun getPosts(): Either<ApiError, List<PostNetworkModel>>

    @GET
    suspend fun getPost(
        @Url url: String,
    ): Either<ApiError, PostNetworkModel>

    companion object {
        fun postDetailUrl(postId: Long) = "${BuildKonfig.API_BASE_URL}/posts/$postId/"
    }
}
