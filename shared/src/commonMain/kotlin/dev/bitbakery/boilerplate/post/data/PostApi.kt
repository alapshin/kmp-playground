package dev.bitbakery.boilerplate.post.data

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.GET
import dev.bitbakery.boilerplate.network.ApiError

interface PostApi {
    @GET("posts/")
    suspend fun getPosts(): Either<ApiError, List<PostNetworkModel>>
}
