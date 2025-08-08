package br.com.parreira.kmpcrudsimples.domain.usecase.post

import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository

class CreatePostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(title: String, body: String) = repository.createPost(title, body)
}
