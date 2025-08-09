package br.com.parreira.kmpcrudsimples.data.repository

import br.com.parreira.kmpcrudsimples.data.dto.PostDto
import br.com.parreira.kmpcrudsimples.data.remote.PostService
import br.com.parreira.kmpcrudsimples.domain.model.Post
import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository

class PostRepositoryImpl(private val api: PostService) : PostRepository {
    override suspend fun getPosts(): Result<List<Post>> {
        return try {
            val postDtos = api.getAll()
            val posts = postDtos.map { Post(id = it.id ?: 0, title = it.title, body = it.body) }
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createPost(title: String, body: String): Result<Post> {
        return try {
            val dto = PostDto(id = null, userId = 1, title = title, body = body)
            val resultDto = api.create(dto)
            Result.success(
                Post(
                    id = resultDto.id ?: 0,
                    title = resultDto.title,
                    body = resultDto.body
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePost(post: Post): Result<Post> {
        return try {
            val dto = PostDto(id = post.id, userId = 1, title = post.title, body = post.body)
            val resultDto = api.update(post.id, dto)
            Result.success(
                Post(
                    id = resultDto.id ?: 0,
                    title = resultDto.title,
                    body = resultDto.body
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePost(id: Int): Result<Unit> {
        return try {
            api.delete(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
