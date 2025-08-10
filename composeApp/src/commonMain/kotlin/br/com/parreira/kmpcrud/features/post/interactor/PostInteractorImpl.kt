package br.com.parreira.kmpcrud.features.post.interactor

import br.com.parreira.kmpcrud.features.post.data.repository.PostRepository
import br.com.parreira.kmpcrud.features.post.entity.Post
import br.com.parreira.kmpcrudsimples.features.post.interactor.PostInteractor

class PostInteractorImpl(private val postRepository: PostRepository) : PostInteractor {
    override suspend fun getAll(): Result<List<Post>> = postRepository.getAll()
    override suspend fun create(title: String, body: String): Result<Post> =
        postRepository.create(title, body)

    override suspend fun update(post: Post): Result<Post> = postRepository.update(post)
    override suspend fun delete(id: Int): Result<Unit> = postRepository.delete(id)
}
