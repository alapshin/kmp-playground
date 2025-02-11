package dev.bitbakery.boilerplate.post.service

interface PostService {
    fun getPosts(): List<Post>

    fun getPost(postId: Long): Post
}
