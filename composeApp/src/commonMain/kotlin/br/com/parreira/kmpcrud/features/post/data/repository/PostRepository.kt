package br.com.parreira.kmpcrud.features.post.data.repository

import br.com.parreira.kmpcrud.features.post.entity.Post

interface PostRepository {
    suspend fun getAll(): Result<List<Post>>
    suspend fun create(title: String, body: String): Result<Post>
    suspend fun update(post: Post): Result<Post>
    suspend fun delete(id: Int): Result<Unit>
}
