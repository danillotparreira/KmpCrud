package br.com.parreira.kmpcrudsimples.data.repository

import br.com.parreira.kmpcrudsimples.data.dto.PostDto
import br.com.parreira.kmpcrudsimples.data.mapper.toDomain
import br.com.parreira.kmpcrudsimples.data.remote.ApiService
import br.com.parreira.kmpcrudsimples.domain.model.Post
import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository

class PostRepositoryImpl(private val apiService: ApiService) : PostRepository {
    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val postDtos = apiService.getPosts()
            val posts = postDtos.map { Post(id = it.id ?: 0, title = it.title, body = it.body) }
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createPost(title: String, body: String): Result<Post> {
        return try {
            val dto = PostDto(id = null, userId = 1, title = title, body = body)
            val resultDto = apiService.createPost(dto)
            Result.success(Post(id = resultDto.id ?: 0, title = resultDto.title, body = resultDto.body))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePost(post: Post): Result<Post> {
        return try {
            val dto = PostDto(id = post.id, userId = 1, title = post.title, body = post.body)
            val resultDto = apiService.updatePost(post.id, dto)
            Result.success(Post(id = resultDto.id ?: 0, title = resultDto.title, body = resultDto.body))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePost(id: Int): Result<Unit> {
        return try {
            apiService.deletePost(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
