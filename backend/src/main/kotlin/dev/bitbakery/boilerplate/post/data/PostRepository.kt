package dev.bitbakery.boilerplate.post.data

interface PostRepository {
    fun getPosts(): List<PostEntity>

    fun getPost(postId: Long): PostEntity
}
