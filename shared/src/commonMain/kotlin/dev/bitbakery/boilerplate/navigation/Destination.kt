package dev.bitbakery.boilerplate.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object Home : Destination

    @Serializable
    data object Start : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Registration : Destination

    @Serializable
    data object PostList : Destination

    @Serializable
    data class PostDetail(
        val postId: Long,
    ) : Destination
}
