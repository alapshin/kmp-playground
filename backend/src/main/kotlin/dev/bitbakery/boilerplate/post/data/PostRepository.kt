package dev.bitbakery.boilerplate.post.data

interface PostRepository {
    fun getPosts(): List<PostEntity>
}
