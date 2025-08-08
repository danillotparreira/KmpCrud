package br.com.parreira.kmpcrudsimples.domain.usecase.post

import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository

class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke() = postRepository.getPosts()
}