package br.com.parreira.kmpcrudsimples.domain.repository

import br.com.parreira.kmpcrudsimples.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun createPost(title: String, body: String): Result<Post>
    suspend fun updatePost(post: Post): Result<Post>
    suspend fun deletePost(id: Int): Result<Unit>
}
