package br.com.parreira.kmpcrudsimples.domain.usecase.post

import br.com.parreira.kmpcrudsimples.domain.model.Post
import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository

class UpdatePostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(post: Post) = repository.updatePost(post)
}
